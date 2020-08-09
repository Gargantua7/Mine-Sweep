package pers.gargantua.mine_sweep

/**
 * @author Gargantua丶
 **/

class Config(val r: Int, val c: Int, val m: Int)

object PresetConfig {
    val EASY = Config(8, 8, 10)
    val ORD = Config(16, 16, 40)
    val HEAD = Config(16, 32, 99)
}
