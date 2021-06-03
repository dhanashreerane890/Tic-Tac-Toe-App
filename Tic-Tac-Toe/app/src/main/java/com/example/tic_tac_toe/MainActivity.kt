package com.example.tic_tac_toe

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PLAYER_O = 0
    var PLAYER_X = 1
    var activePlayer = PLAYER_O

    var filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
    var isGameActive = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        header_text.text="PLAYER O"

        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)


        btnRestart.setOnClickListener {
            activePlayer = PLAYER_O
            header_text.text = "Player O"
            filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
            btn0.text = ""
            btn1.text = ""
            btn2.text = ""
            btn3.text = ""
            btn4.text = ""
            btn5.text = ""
            btn6.text = ""
            btn7.text = ""
            btn8.text = ""
            isGameActive = true
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onClick(v: View?) {
        if(!isGameActive){
            return
        }

        val clickBtn= v?.let { findViewById<Button>(it.id) }
        val clickTag= v?.tag.toString().toInt()

        if(filledPos[clickTag] != -1){
            return
        }
        filledPos[clickTag] = activePlayer;

        if(activePlayer == PLAYER_O){
            clickBtn?.text = "O"
            activePlayer = PLAYER_X
            header_text.text = "PLAYER X"
        }else{
            clickBtn?.text = "X";
            activePlayer = PLAYER_O;
            header_text.text = "PLAYER O";
        }
        checkForWin()
    }

    private fun checkForWin() {
        val winningPos = arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8), intArrayOf(0, 3, 6),
                intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
                intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )

        for (i in 0..7) {
            val val0 = winningPos[i][0]
            val val1 = winningPos[i][1]
            val val2 = winningPos[i][2]
            if (filledPos[val0] ==filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if (filledPos[val0] != -1) {
                    //winner declare
                    isGameActive = false
                    if (filledPos[val0] == PLAYER_O) {
                        showDialog("O is winner")
                    } else {
                        showDialog("X is winner")
                    }
                }
            }
        }
    }
    private fun showDialog(winnerText: String) {
        AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game",
                        DialogInterface.OnClickListener { dialog, which -> restartGame() })
                .show()
    }

    private fun restartGame() {
        activePlayer = PLAYER_O
        header_text.text = "Player O"
        filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
        btn0.text = ""
        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""
        isGameActive = true
    }

}