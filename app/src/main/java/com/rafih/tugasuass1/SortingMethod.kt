package com.rafih.tugasuass1

object SortingMethod {

    fun bubbleSort(item: MutableList<Int>): MutableList<Int> {
        val itemSize = item.size - 1

        for(i in 0..itemSize){
            for (j in i..itemSize){
                if (item[i] > item[j]){ // 4,2,1,3
                    var temp = item[i]
                    item[i] = item[j]
                    item[j] = temp
                }
            }
        }
        return item
    }

    fun selectionSort(item: MutableList<Int>): MutableList<Int> {
        val itemSize = item.size - 1

        for (i in 0..itemSize){ //range(0,itemSize)
            var minIdx = i
            for (j in minIdx + 1..itemSize)
                if (item[j] < item[minIdx]){ //1,3,2,4
                    minIdx = j
                }

            var temp = item[minIdx]
            item[minIdx] = item[i]
            item[i] = temp
        }
        return item
    }

    fun insertionSort(item: MutableList<Int>): MutableList<Int> {
        val itemSize = item.size - 1

        for (i in 1..itemSize){
            val key = item[i] //2
            var j = i - 1 //1

            while (j >= 0 && item[j] > key){ //3,2,4,1
                item[j + 1] = item[j]
                j -= 1
            }
            item[j + 1] = key
        }
        return item
    }
}