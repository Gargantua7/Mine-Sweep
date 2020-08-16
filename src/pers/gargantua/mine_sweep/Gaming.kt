package pers.gargantua.mine_sweep

import java.awt.GridLayout
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

/**
 * @author Gargantua丶
 **/
object Gaming : JPanel(), MouseListener {

    private val gridLayout = GridLayout()

    init {
        layout = gridLayout
    }

    fun start() {
        removeAll()
        Maps.initialize()
        gridLayout.rows = Maps.config.r
        gridLayout.columns = Maps.config.c
        MainFrame.setSize(Maps.config.c * 50, Maps.config.r * 50 + 70)
        for (i in 0 until Maps.config.r) {
            for (j in 0 until Maps.config.c) {
                val button = Maps[i][j]
                button.addMouseListener(this)
                this.add(button)
            }
        }
    }

    private fun superKnock(button: Ground) {
        if (button.text != "") return
        val around = button.around
        button.setNor()
        if (around == 0) {
            val r = button.r
            val c = button.c
            for (i in r - 1..r + 1) {
                for (j in c - 1..c + 1) {
                    if ((i != r || j != c) && i in 0 until Maps.config.r && j in 0 until Maps.config.c) {
                        superKnock(Maps[i][j])
                    }
                }
            }
        }
    }

    private fun judge(): Boolean {
        if (StaBar.mines != Maps.config.m) return false
        for (r in 0 until Maps.config.r) {
            for (c in 0 until Maps.config.r) {
                if (Maps[r][c].text == "F" && !Maps[r][c].isMine) {
                    return false
                }
            }
        }
        return true
    }


    private fun buttonTurn(button: Ground) {
        if (button.text == "F" || button.r !in 0 until Maps.config.r || button.c !in 0 until Maps.config.c)
            return
        if (button.isMine) {
            button.setMine()
            gameOver()
        } else {
            superKnock(button)
        }
    }

    private fun midJudge(button: Ground) {
        if (button.text in listOf("", "F", "M", "?", "0")) return
        var quantityOfMarked = 0
        val r = button.r
        val c = button.c
        for (i in r - 1..r + 1) {
            for (j in c - 1..c + 1) {
                if ((i != r || j != c) && i in 0 until Maps.config.r && j in 0 until Maps.config.c &&
                    Maps[i][j].text == "F"
                ) {
                    quantityOfMarked++
                }
            }
        }
        if (quantityOfMarked == button.around) {
            for (i in r - 1..r + 1) {
                for (j in c - 1..c + 1) {
                    if ((i != r || j != c) && i in 0 until Maps.config.r && j in 0 until Maps.config.c) {
                        buttonTurn(Maps[i][j])
                    }
                }
            }
        }
    }

    private fun gameOver() {
        for (i in 0 until Maps.config.r) {
            for (j in 0 until Maps.config.c) {
                Maps[i][j].apply {
                    if (text == "F" && !isMine) {
                        foreground = MyStyle.M
                        text = "X"
                    } else if (text != "F" && isMine) {
                        setMine()
                    }
                }
            }
        }
        MyDialog(context = "Game Over")
    }

    override fun mouseReleased(e: MouseEvent?) {
    }

    override fun mouseEntered(e: MouseEvent?) {
        e?.source.apply {
            this as Ground
            if (text == "") background = MyStyle.CHOOSE
        }
    }

    override fun mouseClicked(e: MouseEvent?) {
        e?.source.apply {
            this as Ground
            when (e?.button) {
                MouseEvent.BUTTON1 -> buttonTurn(this)
                MouseEvent.BUTTON2 -> midJudge(this)
                MouseEvent.BUTTON3 -> when (text) {
                    "" -> {
                        setFlag()
                        StaBar.mines++
                        if (judge()) {
                            MyDialog(
                                context = "<html><body><p align=\"center\">Congratulation !<br/>Times: "
                                        + StaBar.times + " s</p></body></html>"
                            )
                        }
                    }
                    "F" -> {
                        setQue()
                        StaBar.mines--
                    }
                    "?" -> setNew()
                }
            }
        }
    }

    override fun mouseExited(e: MouseEvent?) {
        e?.source.apply {
            this as Ground
            if (text !in listOf("F", "M", "?", "0")) background = MyStyle.B
        }
    }

    override fun mousePressed(e: MouseEvent?) {
    }

}


