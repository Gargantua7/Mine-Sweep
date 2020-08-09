package pers.gargantua.mine_sweep

import javax.swing.JButton

/**
 * @author Gargantua丶
 **/
class Ground(val r: Int, val c: Int, var isMine: Boolean = false, var around: Int = 0) : JButton() {

    init {
        font = MyStyle.FONT_B
        background = MyStyle.B
    }

    fun setMine() {
        text = "M"
        background = MyStyle.M
    }

    fun setFlag() {
        text = "F"
        background = MyStyle.F
    }

    fun setNor(n: Int) {
        if (n == 0)
            background = MyStyle.ZERO
        text = n.toString()
        foreground = MyStyle.NUMBER[n]
    }

    fun setQue() {
        text = "?"
        background = MyStyle.B
    }

    fun setNew() {
        text = ""
        background = MyStyle.B
    }
}