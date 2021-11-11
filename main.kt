package parking

fun main() {
    var isRunning = true
    var spots: MutableList<Spot>? = null
    while (isRunning) {
        val input = readLine()!!.split(" ")
        when (input[0]) {
            "create" -> {
                spots = MutableList<Spot>(input[1].toInt()) { Spot(it, false, "", "") }
                println("Created a parking lot with ${input[1]} spots.")
            }
            "park" -> {
                if (spots != null) {
                    val freeSpot = getFreeSpot(spots)
                    if (freeSpot != -1) {
                        println("${input[2]} car parked in spot ${freeSpot + 1}.")
                        spots[freeSpot].isOcuppied = true
                        spots[freeSpot].carPlate = input[1]
                        spots[freeSpot].carColor = input[2]
                    } else {
                        println("Sorry, the parking lot is full.")
                    }
                } else {
                    println("Sorry, a parking lot has not been created.")
                }
            }
            "leave" -> {
                if (spots != null) {
                    val number = input[1].toInt() - 1
                    if (spots[number].isOcuppied) {
                        spots[number] = Spot(number, false, "", "")
                        println("Spot ${input[1]} is free.")
                    } else {
                        println("There is no car in spot ${input[1]}.")
                    }
                } else {
                    println("Sorry, a parking lot has not been created.")
                }
            }
            "status" -> {
                if (spots != null) {
                    var isAllSpotsFree = true
                    for (spot in spots) {
                        if (spot.isOcuppied) {
                            isAllSpotsFree = false
                            println("${spot.number + 1} ${spot.carPlate} ${spot.carColor}")
                        }
                    }
                    if (isAllSpotsFree) {
                        println("Parking lot is empty.")
                    }
                } else {
                    println("Sorry, a parking lot has not been created.")
                }
            }
            "reg_by_color" -> {
                if (spots != null) {
                    var carPlates = mutableListOf<String>()
                    var isCarFound = false
                    for (spot in spots) {
                        if (spot.carColor.lowercase() == input[1].lowercase()) {
                            isCarFound = true
                            carPlates.add(spot.carPlate)
                        }
                    }
                    if (!isCarFound) {
                        println("No cars with color ${input[1]} were found.")
                    } else {
                        println(carPlates.joinToString())
                    }
                } else {
                    println("Sorry, a parking lot has not been created.")
                }
            }
            "spot_by_color" -> {
                if (spots != null) {
                    var spotsNums = mutableListOf<Int>()
                    var isCarFound = false
                    for (spot in spots) {
                        if (spot.carColor.lowercase() == input[1].lowercase()) {
                            isCarFound = true
                            spotsNums.add(spot.number + 1)
                        }
                    }
                    if (!isCarFound) {
                        println("No cars with color ${input[1]} were found.")
                    } else {
                        println(spotsNums.joinToString())
                    }
                } else {
                    println("Sorry, a parking lot has not been created.")
                }
            }
            "spot_by_reg" -> {
                if (spots != null) {
                    var isCarFound = false
                    for (spot in spots) {
                        if (spot.carPlate == input[1]) {
                            isCarFound = true
                            println(spot.number + 1)
                        }
                    }
                    if (!isCarFound) {
                        println("No cars with registration number ${input[1]} were found.")
                    }
                } else {
                    println("Sorry, a parking lot has not been created.")
                }
            }
            "exit" -> isRunning = false
        }
    }
}

fun getFreeSpot(spots: MutableList<Spot>?): Int {
    if (spots != null) {
        for (i in 0..spots.size - 1) {
            if (spots[i] != null && !spots[i].isOcuppied) {
                return i
            }
        }
    }
    return -1
}

data class Spot(
    val number: Int,
    var isOcuppied: Boolean,
    var carPlate: String,
    var carColor: String
)
