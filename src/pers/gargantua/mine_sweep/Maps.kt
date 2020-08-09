package pers.gargantua.mine_sweep

import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Gargantua丶
 **/
object Maps : ArrayList<ArrayList<Ground>>() {

    lateinit var config: Config

    fun initialize() {
        clear()
        for (i in 0 until config.r) {
            add(ArrayList<Ground>().apply {
                for (j in 0 until config.c)
                    this.add(Ground(i, j))
            })
        }

        val random = Random()
        for (i in 0 until config.m) {
            while (true) {
                val ground = this[random.nextInt(config.r)][random.nextInt(config.c)]
                if (!ground.isMine) {
                    ground.isMine = true
                    break
                }
            }
        }

        for (i in 0 until config.r) {
            for (j in 0 until config.c) {
                if (this[i][j].isMine) continue
                var quantityOfMine = 0
                for (r in i - 1..i + 1) {
                    for (c in j - 1..j + 1) {
                        if ((i != r || j != c) && r in 0 until config.r && c in 0 until config.c &&
                            this[r][c].isMine) {
                            quantityOfMine++
                        }
                    }
                }
                this[i][j].around = quantityOfMine
            }
        }

        for (grounds in this) {
            for (ground in grounds) print("${ground.around} ")
            println()
        }
    }
}