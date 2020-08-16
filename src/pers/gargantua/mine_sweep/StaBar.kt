package pers.gargantua.mine_sweep

import java.awt.GridLayout
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * @author Gargantua丶
 **/
object StaBar : JPanel() {

    var times: Int = 0
    var mines: Int = 0

    private val time = JLabel().apply { font = MyStyle.FONT_B }
    private val mine = JLabel().apply {
        font = MyStyle.FONT_B
        horizontalAlignment = JLabel.RIGHT
    }

    private var sta = false

    fun run() {
        Thread {
            sta = true
            time.text = "Times: 0"
            while (sta) {
                var con = 0
                while(con++ < 10) {
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mine.text = "$mines/${Maps.config.m}"
                }
                times++
                time.text = "Times: $times"
            }
        }.start()
    }

    init {
        layout = GridLayout(1, 3)
        add(time)
        add(mine)
    }

    fun stop() {
        sta = false
    }
}