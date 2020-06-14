package com.annaalfiani.gmcapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.annaalfiani.gmcapps.models.Kursi
import com.murgupluoglu.seatview.Seat
import com.murgupluoglu.seatview.SeatViewConfig
import com.murgupluoglu.seatview.SeatViewListener
import kotlinx.android.synthetic.main.activity_seat.*

class SeatActivity : AppCompatActivity() {
    private lateinit var seatActivityViewModel: SeatActivityViewModel
    private val rowNames: java.util.HashMap<String, String> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)
        setupViewModel()
        setSeatInfo()
        setupSeatView()
        setupSeatViewBehavior()
    }

    private fun setupViewModel(){
        seatActivityViewModel = ViewModelProvider(this).get(SeatActivityViewModel::class.java)
    }


    private fun setSeatInfo(){
        seatActivityViewModel.setSeatInfo(getSeats())
    }

    private fun getSeats() = intent.getParcelableExtra<Kursi>("seat_info")!!

    private fun setupSeatView(){
        seatView.config.zoomActive = true
        seatView.config.zoomAfterClickActive = true
        seatView.seatClickListener = object : SeatViewListener {
            override fun seatReleased(releasedSeat: Seat, selectedSeats: java.util.HashMap<String, Seat>) {
                Toast.makeText(this@SeatActivity, "Released->" + releasedSeat.seatName, Toast.LENGTH_SHORT).show()
            }

            override fun seatSelected(selectedSeat: Seat, selectedSeats: java.util.HashMap<String, Seat>) {
                Toast.makeText(this@SeatActivity, "Selected->" + selectedSeat.seatName, Toast.LENGTH_SHORT).show()
            }

            override fun canSelectSeat(clickedSeat: Seat, selectedSeats: java.util.HashMap<String, Seat>): Boolean {
                return clickedSeat.type != Seat.TYPE.UNSELECTABLE
            }
        }
    }

    private fun setupSeatViewBehavior(){
        val letterCount = seatActivityViewModel.listenToSeatInfo().value!!.totalRow!!
        val numberSeatCount = seatActivityViewModel.listenToSeatInfo().value!!.totalColumn!!.toInt()
        val seatArray = Array(letterCount) { Array(numberSeatCount) { Seat() } }
        println("Array size ${seatArray.size}")

        val rowArray = seatActivityViewModel.listenToSeatInfo().value!!.seats
        val transformedData = transformData(seatArray, rowNames, rowArray, letterCount, numberSeatCount)


        seatView.initSeatView(transformedData, letterCount, numberSeatCount, rowNames)
    }

    private fun saveRowNames(){
        val grouped = seatActivityViewModel.listenToSeatInfo().value!!.seats.distinctBy { it.nama_kursi.toString().split("-")[1] }
        for( (index, seat) in grouped.withIndex()){
            rowNames.put(index.toString(), seat.nama_kursi.toString().split("-")[1])
        }
    }

    private fun transformData(seatArray: Array<Array<Seat>>, rowNames: HashMap<String, String>, rowArray: List<com.annaalfiani.gmcapps.models.Seat>, rowCount: Int, columnCount: Int): Array<Array<Seat>>{
        saveRowNames()
        for (i in rowArray.indices){
            val letterPosition = getHashMapKeyByValue(rowArray[i].nama_kursi.toString().split("-")[1])
            val numberOfSeat = rowArray[i].nama_kursi.toString().split("-")[0]
            val movieSeat = Seat().apply {
                id = rowArray[i].id.toString()
                rowName = rowArray[i].nama_kursi.toString().substring(0, 1)
                rowIndex = letterPosition.toInt()
                columnIndex =  numberOfSeat.toInt()
                isSelected = false
                type = Seat.TYPE.SELECTABLE
                seatName = rowArray[i].nama_kursi.toString()
                drawableResourceName = "seat_available"
                selectedDrawableResourceName = "seat_selected"


            }
            seatArray[letterPosition.toInt()][numberOfSeat.toInt()-1] = movieSeat
        }
        return seatArray
    }

    private fun getHashMapKeyByValue(value: String): String {
        var found = ""
        rowNames.forEach { (key, v) ->
            if(v.equals(value)){
                found = key
                return@forEach
            }
        }
        return found
    }
}
