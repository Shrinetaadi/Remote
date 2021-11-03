package com.catvisionindia.remote

import android.content.Context
import android.content.Intent
import android.hardware.ConsumerIrManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Vibrator
import android.view.View
import com.catvisionindia.remote.R
import com.mitsest.spotlightviewpager.model.SpotlightViewModel
import com.mitsest.spotlightviewpager.model.SubtitleModel
import com.mitsest.spotlightviewpager.view.SpotlightView
import java.util.*


class MainActivity : AppCompatActivity() {
    private var moreClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSkiptt.setOnClickListener {
            tutorialframe.visibility = View.GONE
        }
        btnNexttt.setOnClickListener {
            tutorialframe.visibility = View.GONE
            tutorial()
        }





        btnPower.setOnClickListener {
            parseFrequency(CatvisionRemote().power)
        }
        imgVolumeUp.setOnClickListener {
            parseFrequency(CatvisionRemote().volUp)
        }
        imgVolumeDown.setOnClickListener {
            parseFrequency(CatvisionRemote().volDown)
        }
        imgHome.setOnClickListener {
            parseFrequency(CatvisionRemote().menu)
        }
        imgMute.setOnClickListener {
            parseFrequency(CatvisionRemote().mute)
        }
        imgChannelUp.setOnClickListener {
            parseFrequency(CatvisionRemote().chUp)
        }
        imgChannelDown.setOnClickListener {
            parseFrequency(CatvisionRemote().chDown)

        }
        btnRedHotkey.setOnClickListener {
            parseFrequency(CatvisionRemote().red)
        }
        btnGreenHotkey.setOnClickListener {
            parseFrequency(CatvisionRemote().green)
        }
        btnYellowHotkey.setOnClickListener {
            parseFrequency(CatvisionRemote().yellow)
        }
        btnBlueHotkey.setOnClickListener {
            parseFrequency(CatvisionRemote().blue)
        }
        btnMenu.setOnClickListener {
            parseFrequency(CatvisionRemote().menu)
        }
        btnRecall.setOnClickListener {
            parseFrequency(CatvisionRemote().recall)
        }
        btnTopNav.setOnClickListener {
            parseFrequency(CatvisionRemote().up)
        }
        btnBottomNav.setOnClickListener {
            parseFrequency(CatvisionRemote().down)
        }
        btnLeftNav.setOnClickListener {
            parseFrequency(CatvisionRemote().left)
        }
        btnRightNav.setOnClickListener {
            parseFrequency(CatvisionRemote().right)
        }
        btnOk.setOnClickListener {
            parseFrequency(CatvisionRemote().ok)
        }
        btnRealOK.setOnClickListener {
            parseFrequency(CatvisionRemote().ok)
        }
        btnEPG.setOnClickListener {
            parseFrequency(CatvisionRemote().epg)
        }
        btnExit.setOnClickListener {
            parseFrequency(CatvisionRemote().exit)
        }
        btnInfo.setOnClickListener {
            parseFrequency(CatvisionRemote().info)
        }
        btnFav.setOnClickListener {
            parseFrequency(CatvisionRemote().fav)
        }
        btnRewind.setOnClickListener {
            parseFrequency(CatvisionRemote().rewind)
        }
        btnRecord.setOnClickListener {
            parseFrequency(CatvisionRemote().recorde)
        }
        btnStop.setOnClickListener {
            parseFrequency(CatvisionRemote().stop)
        }
        btnPlay.setOnClickListener {
            parseFrequency(CatvisionRemote().play)
        }
        btnPause.setOnClickListener {
            parseFrequency(CatvisionRemote().pause)
        }
        btnForward.setOnClickListener {
            parseFrequency(CatvisionRemote().forward)
        }
        btnNum0.setOnClickListener {
            parseFrequency(CatvisionRemote().num0)
        }
        btnNum1.setOnClickListener {
            parseFrequency(CatvisionRemote().num1)
        }
        btnNum2.setOnClickListener {
            parseFrequency(CatvisionRemote().num2)
        }
        btnNum3.setOnClickListener {
            parseFrequency(CatvisionRemote().num3)
        }
        btnNum4.setOnClickListener {
            parseFrequency(CatvisionRemote().num4)
        }
        btnNum5.setOnClickListener {
            parseFrequency(CatvisionRemote().num5)
        }
        btnNum6.setOnClickListener {
            parseFrequency(CatvisionRemote().num6)
        }
        btnNum7.setOnClickListener {
            parseFrequency(CatvisionRemote().num7)
        }
        btnNum8.setOnClickListener {
            parseFrequency(CatvisionRemote().num8)
        }
        btnNum9.setOnClickListener {
            parseFrequency(CatvisionRemote().num9)
        }
        btnTVRadio.setOnClickListener {
            parseFrequency(CatvisionRemote().tvRadio)
        }
        btnAudio.setOnClickListener {
            parseFrequency(CatvisionRemote().audio)
        }
        imgKeypad.setOnClickListener {
            if (moreClicked == 0) {
                var params = llDailPad.layoutParams
                params.height = 1000
                llDailPad.layoutParams = params
                moreClicked = 1
                imgKeypad?.setImageResource(R.drawable.ic_cancel)
            } else if (moreClicked == 1) {
                var params = llDailPad.layoutParams
                params.height = 0
                llDailPad.layoutParams = params
                imgKeypad?.setImageResource(R.drawable.ic_dialpad)
                moreClicked = 0

            }
        }
        frameKeypad.setOnClickListener {
            if (moreClicked == 0) {
                var params = llDailPad.layoutParams
                params.height = 1000
                llDailPad.layoutParams = params
                moreClicked = 1
                imgKeypad?.setImageResource(R.drawable.ic_cancel)
            } else if (moreClicked == 1) {
                var params = llDailPad.layoutParams
                params.height = 0
                llDailPad.layoutParams = params
                imgKeypad?.setImageResource(R.drawable.ic_dialpad)
                moreClicked = 0

            }
        }
        imgViewBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun parseFrequency(irData: String) {
        val vibe = this@MainActivity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibe.vibrate(60)

        val list = irData.split(" ").toMutableList()
        list.removeAt(0)
        var frequency: Int = list.removeAt(0).toInt(16) // frequency
        list.removeAt(0)
        list.removeAt(0)
        frequency = (1000000 / (frequency * 0.241246)).toInt()
        val pulses = 1000000 / frequency
        var count: Int
        val pattern = IntArray(list.size)
        for (i in list.indices) {
            count = list[i].toInt(16)
            pattern[i] = count * pulses
        }
        val irManager: ConsumerIrManager =
            this@MainActivity.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

        irManager.transmit(frequency, pattern)
    }

    fun tutorial() {
        val viewModel1 = SpotlightViewModel(
            "Number Pad", SubtitleModel("Click on this to open number pad.", 7), frameKeypad
        )
        val viewModel2 = SpotlightViewModel(
            "Volume Controls", SubtitleModel("Click on this to control volumes.", 7), cvVolume
        )
        val viewModel3 = SpotlightViewModel(
            "Channel Controls", SubtitleModel("Click on this to control channels.", 7), cvChannel
        )

        val viewModel4 = SpotlightViewModel(
            "Yellow Hot Key",
            SubtitleModel("Click on this to open channel settings.", 7),
            btnYellowHotkey
        )
        val viewModel5 = SpotlightViewModel(
            "Blue Hot Key", SubtitleModel("Click on this to open card details.", 7), btnBlueHotkey
        )

        val viewModel6 = SpotlightViewModel(
            "Home",
            SubtitleModel("Click on this to open menu.", 7), imgHome
        )
        val viewModel7 =
            SpotlightViewModel("Mute", SubtitleModel("Click on this to mute.", 7), imgMute)


        SpotlightView.addSpotlightView(
            this,
            listOf(
                viewModel2,
                viewModel6,
                viewModel1,
                viewModel7,
                viewModel3,
                viewModel4,
                viewModel5
            )
        )
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}