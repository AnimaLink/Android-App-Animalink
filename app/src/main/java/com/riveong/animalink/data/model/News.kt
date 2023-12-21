package com.riveong.animalink.data.model

import com.riveong.animalink.R

data class News(
    val image: String,
    val title: String,
    val short: String
)

val NewsDummy = listOf(
    News("https://worldanimalfoundation.org/wp-content/uploads/2022/08/jellyfish-review.jpg", "Apakah ubur - ubur sebenarnya ikan?", "Ubur - ubur atau yang disebut dengan sea jellies sebenarnya bukanlah ikan namun..."),
    News("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWERBy4HGKZoccbNHwzn38P7SZLLZ3_3kGZg&usqp=CAU", "Kucing bisa menyembuhkan depresi??", "Kucing ternyata bisa sembuhkan depresi! Siapa nih yang barusan tahu kucing bisa sembuhkan depresi? Yap apakah kalian pernah perhatikan..."),
    News("https://statik.tempo.co/data/2023/10/01/id_1241501/1241501_720.jpg", "Badak Sumatera yang sangat terancam lahir di Indonesia", "Sebuah penangkaran di Indonesia merayakan kelahiran badak Sumatera, spesies badak yang paling terancam di dunia..."),
    News("https://queenmobs.s3.eu-west-2.amazonaws.com/wp-content/uploads/2018/10/28122935/sad-animals-11.jpg", "Indonesia berada di peringkat tinggi dalam perdagangan satwa liar legal", "Indonesia berada di peringkat 9 dalam daftar 80 negara dengan jumlah spesimen satwa liar yang secara legal diekspor ke luar negeri sejak tahun 1975, menunjukkan penelitian baru..."),
    News("https://kaltimtoday.co/wp-content/uploads/2022/06/ilustrasi-badak.jpg", "Badak Sumatera langka lahir di Indonesia", "Sebuah badak Sumatera langka telah lahir di sebuah penangkaran di Indonesia. Anak badak, yang belum diberi nama, memiliki berat 25kg...")

)