package mohsen.coder.dto

data class DateDTO(val year: Int, val month: Int, val dayOfMonth: Int){
    override fun toString(): String {
        return "$year/$month/$dayOfMonth"
    }
}