package pers.gargantua.mine_sweep

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

/**
 * @author Gargantua丶
 **/
object Start : JPanel() {

    private val gridBagLayout = GridBagLayout()
    private val first = GridBagConstraints().apply {
        fill = GridBagConstraints.BOTH
    }
    private val last = GridBagConstraints().apply {
        fill = GridBagConstraints.BOTH
        gridwidth = GridBagConstraints.REMAINDER
    }

    private val r = MyLabel("Row :", gridBagLayout, first)
    private val c = MyLabel("Column :", gridBagLayout, first)
    private val m = MyLabel("Mines :", gridBagLayout, first)

    private val row = MyText("", gridBagLayout, last)
    private val column = MyText("", gridBagLayout, last)
    private val mines = MyText("", gridBagLayout, last)

    private val easy = MyButton("EASY", gridBagLayout, first).apply {
        addActionListener {
            setting(PresetConfig.EASY)
        }
    }
    private val ord = MyButton("ORD", gridBagLayout, first).apply {
        addActionListener {
            setting(PresetConfig.ORD)
        }
    }
    private val hard = MyButton("HEAD", gridBagLayout, last).apply {
        addActionListener {
            setting(PresetConfig.HEAD)
        }
    }
    private val start = MyButton("Game Start!", gridBagLayout, last).apply {
        addActionListener {
            var res = true
            var r = 0
            var c = 0
            var m = 0
            try {
                r = row.text.toInt()
                c = column.text.toInt()
                m = mines.text.toInt()
            } catch (e: NumberFormatException) {
                res = false
                MyDialog("ERROR", "Invalid Value", sta = false)
            }
            if(res) {
                if(r < 1 || c < 1){
                    MyDialog("ERROR", "Too few rows or columns", sta = false)
                } else if(m < 1){
                    MyDialog("ERROR", "Too few mines", sta = false)
                } else if (r * c < m) {
                    MyDialog( "ERROR", "Too many mines", sta = false)
                } else {
                    MainFrame.cardLayout.show(MainFrame.mainPanel, "gaming")
                    Maps.config = Config(r, c, m)
                    Gaming.start()
                    StaBar.mines = 0
                    StaBar.times = 0
                    StaBar.run()
                }
            }
        }
    }

    init {
        layout = gridBagLayout

        add(r)
        add(row)
        add(c)
        add(column)
        add(m)
        add(mines)
        add(easy)
        add(ord)
        add(hard)
        add(start)
    }

    private fun setting(config: Config) {
        row.text = config.r.toString()
        column.text = config.c.toString()
        mines.text = config.m.toString()
    }

}
