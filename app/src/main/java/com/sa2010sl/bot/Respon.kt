package com.sa2010sl.bot;

public object Respon {

object notificationreceiver {
const val notificationreceiver1 = """
▬▬ ACCESS DENIED ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Data karakter
tdk ditemukan! Pastikan
daftar terlebih dahulu.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver2 = """
▬▬ STATUS HIDUP ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Karaktermu
ternyata masih hidup!

💖 HP saat ini: %d / %d

Kamu tidak butuh ritual
kebangkitan saat ini.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver3 = """
▬▬ KEBANGKITAN ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
✨ RITUAL BERHASIL! ✨

Pemain : %s
Status : Pulih Total
HP Baru: %d / %d
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Jiwa kamu dipanggil dari
kegelapan! Ketik suatu
perintah utk lanjut.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver4 = """
▬▬ SYSTEM ERROR ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Gagal saat
memproses ritual
kebangkitan karakter.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver5 = """
▬▬ FORMAT SALAH ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Silakan ketik
format berikut:
#session
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver6 = """
▬▬ FORMAT SALAH ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Silakan ketik
format berikut:
#session
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver7 = """
▬▬ CMD UNKNOWN ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Perintah tdk
dikenali. Gunakan:

● DAFTAR AKUN BARU
└ #session
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver8 = """
▬▬ USERNAME INVALID ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Username WA
tdk valid/sdh dipakai.

Ganti nama dahulu.
#session
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver9 = """
▬▬ DAFTAR SUKSES ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Pendaftaran
berhasil! Akun kamu
telah dibuat.

ID Akun: #%d
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver10 = """
▬▬ SYSTEM ERROR ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Gagal sistem
saat memproses
pendaftaran akun.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver11 = """
▬▬ VERIFIKASI OK ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Verifikasi
berhasil! Sesi akunmu
kini aktif kembali.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver12 = """
▬▬ X ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Ganti username WA
kamu lebih dulu jika
ingin membuat akun baru.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver13 = """
▬▬ ACCESS DENIED ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Akun kamu
belum terdaftar.
Buat akun baru dengan:
#session
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val notificationreceiver14 = """
▬▬ SYSTEM ERROR ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Gagal sistem
saat memproses session
akun milikmu.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object bank {
    const val bank1 = """
    ▬▬ BANK NOTICE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    [ 𝗬𝗮𝗵𝗿𝘂 ]: Maaf...
    Layanan bank masih tutup.
    Buka  : 10:00 Pagi
    Tutup : 16:00 Sore
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maaf, akun
    kamu belum terdaftar!
    Silakan mendaftar via
    ketik #session terlebih dalu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank3 = """
    ▬▬ BANK COMMANDS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Petunjuk:
    
    ├ #bank
    │ Cek tabungan & dompet
    ├ #bank tabung <nominal>
    │ Setor koin (angka/semua)
    └ #bank tarik <nominal>
      Tarik koin dari bank
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank4 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Perintah
    tidak dikenali. Ketik
    #bank untuk petunjuk.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank5 = """
    ▬▬ LOKET BANK CENTRAL ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● DATA NASABAH
    ├ ID Nasabah : %s
    ├ Koin Dompet: %s 🪙
    └ Koin Bank  : %s 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Selamat datang!
    Ada yang bisa dibantu?
    (*´▽`*)

    • #bank tabung <nominal>
    • #bank tarik <nominal>
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank6 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maaf, terjadi
    kesalahan saat memeriksa
    data bank kamu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank7 = """
    ▬▬ INVALID AMOUNT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Nominal setor
    koin harus lebih dari 0!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank8 = """
    ▬▬ INSUFFICIENT FUNDS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maaf, koin di
    dompet kamu tidak cukup
    untuk ditabung!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank9 = """
    ▬▬ NOTA PENYETORAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● RINCIAN TRANSAKSI
    ├ Penyetoran  : +%d
    ├ Sisa Dompet : %d
    └ Total Bank  : %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Terima kasih sudah
    menabung di bank kami!
    (*´▽`*)
    Penyetoran %d koin telah
    berhasil diproses.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank10 = """
    ▬▬ INVALID FORMAT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Nominal harus
    berupa angka, atau ketik
    'semua'.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank11 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maaf, terjadi
    kesalahan saat memproses
    penyetoran koin.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank12 = """
    ▬▬ LIMIT REACHED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maaf ya...
    Minimal penarikan adalah
    15 koin agar cukup untuk
    potongan admin 10 koin!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank13 = """
    ▬▬ INSUFFICIENT FUNDS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maaf, tabungan
    bank kamu tidak mencukupi
    untuk penarikan ini!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank14 = """
    ▬▬ NOTA PENARIKAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● RINCIAN TRANSAKSI
    ├ Penarikan   : %d
    ├ Biaya Admin : %d
    ├ Diterima    : +%d
    └ Sisa Bank   : %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Terima kasih!
    (*´▽`*)
    Penarikan %d koin sukses.
    Diterima: %d koin setelah
    potongan admin.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bank15 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maaf, terjadi
    kesalahan saat memproses
    penarikan koin.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object bantuan {
    const val bantuan1 = """
    ▬▬ PANDUAN SYSTEM ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #bantuan <kategori>
    untuk melihat detail.
    
    ● KATEGORI BANTUAN
    ├ #bantuan akun
    │ Info profil & ranking
    ├ #bantuan aksi
    │ Jelajah & tambang
    ├ #bantuan ekonomi
    │ Beli, jual & kelola
    └ #bantuan kuis
      Panduan mini game
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Contoh: #bantuan akun
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bantuan2 = """
    ▬▬ PANDUAN AKUN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ├ #menu
    │ Buka menu perintah
    ├ #bantuan
    │ Buka pesan panduan
    ├ #mulai
    │ Daftar akun baru
    ├ #profil
    │ Cek status karakter
    ├ #session
    │ Buat database baru
    ├ #levelup
    │ Tukar EXP ke Level
    ├ #rank
    │ Top 10 Pemain
    ├ #kode <nama_kode>
    │ Klaim hadiah gratis
    └ #ping
      Cek respon
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bantuan3 = """
    ▬▬ PANDUAN AKSI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ├ #jelajah
    │ Cari poin & event
    ├ #berburu
    │ Cari EXP & koin
    ├ #serang
    │ Serang musuh aktif
    ├ #tambang
    │ Cari batu/besi/emas
    ├ #tebang
    │ Cari bahan kayu
    └ #buat <nama_item>
      Racik bahan mentah
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Contoh: #buat pedang
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bantuan4 = """
    ▬▬ PANDUAN EKONOMI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ├ #inventori
    │ Cek isi tas/barang
    ├ #pakai <item>
    │ Gunakan senjata/equip
    ├ #makan <item>
    │ Pulihkan HP karakter
    ├ #beli <item> <jumlah>
    │ Beli dari toko
    ├ #jual <item> <jumlah>
    │ Jual hasil buruan
    └ #slot <taruhan>
      Mesin, slot, judi, koin
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Contoh: #makan apel
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val bantuan5 = """
    ▬▬ PANDUAN KUIS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Format Umum Permainan:
    · <kuis> mulai  (Mulai)
    · <kuis> jawab  (Jawab)
    · <kuis> nyerah (Nyerah)
    
    ● DAFTAR KUIS
    ├ #caklontong
    ├ #siapakahaku
    ├ #tebakkalimat
    ├ #tebakkata
    ├ #tebaklirik
    ├ #tekateki
    ├ #susunkata
    ├ #asahotak
    ├ #tebakbendera
    ├ #tebakkimia
    └ #tebaknegara
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Contoh: #susunkata mulai
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object beli {
    const val beli1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val beli2 = """
    ▬▬ TOKO SISTEM ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koin : %d 🪙
    Ketik: #beli [id] [jml]
    
    ● MAKANAN / KONSUMSI
    %s
    ● SENJATA (WEAPON)
    %s
    ● ARMOR / KETAHANAN
    %s
    ● ITEM SPESIAL
    %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val beli3 = """
    ├ %s (%s)
    │ Hrga: %d 🪙
    │ Efek: %s""";

    const val beli4 = """
    ▬▬ ITEM NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Item %s
    tidak ada di toko.
    Ketik #beli utk daftar.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val beli5 = """
    ▬▬ INSUFFICIENT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koin kurang!
    Harga %dx %s: %d 🪙
    Koin kamu: %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val beli6 = """
    ▬▬ TRANSAKSI SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Item  : %s %s%s
    Biaya : -%d 🪙
    Sisa  : %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    %s""";

    const val beli7 = """
    > Item masuk #inventori""";

    const val beli8 = """
    > AUTO-EQUIP:
    %s (ATK: %d)
    Menggantikan %s.""";

    const val beli9 = """
    > Stat lebih rendah,
    masuk ke #inventori.""";

    const val beli10 = """
    > AUTO-EQUIP:
    %s dipasang!
    Ganti: %s
    Efek : Redam %d%% damage.""";

    const val beli11 = """
    > Stat lebih rendah,
    masuk ke #inventori.""";

    const val beli12 = """
    > SPESIAL: +%d EXP instan!""";

    const val beli13 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses pembelian.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object berburu {
    const val berburu1 = """
    ▬▬ GROUP ONLY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    #berburu
    hanya bisa di grup!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val berburu2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val berburu3 = """
    ▬▬ LOW ENERGY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    EP kurang!
    Biaya   : %d EP ⚡
    EP Kamu : %d EP ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val berburu4 = """
    ▬▬ HASIL BERBURU ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pemburu : %s
    Biaya   : -4 EP
    Sisa EP : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Nihil...
    Kamu menjelajah namun
    tidak menemukan apa pun
    kali ini.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val berburu5 = """
    ├ %s %s: +%d (Tot: %d)""";

    const val berburu6 = """
    ▬▬ HASIL BERBURU ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pemburu : %s
    Lokasi  : %s
    Sisa EP : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● DAPAT BURUAN:
    %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val berburu7 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses perburuan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object boss {
    const val boss1 = """
    ▬▬ WORLD BOSS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Belum ada
    Boss yg bangkit hari ini.
    Jadwal: 17:00-18:00 WIB.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss2 = """
    ▬▬ BOSS DEFEATED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Boss %s
    telah tumbang hari ini!
    Tunggu jadwal esok hari
    pukul 17:00 WIB.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss3 = """
    ▬▬ WORLD BOSS AKTIF ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Nama : %s
    HP   : %d / %d (%d%%)
    Stat : [%s]
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #serangbos untuk
    mengeroyok bersama!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss4 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal membaca
    status World Boss.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss5 = """
    ▬▬ EVENT OFF ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Event ini
    hanya buka setiap hari
    pukul 17:00-18:00 WIB.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss6 = """
    ▬▬ BOSS DEFEATED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Boss telah dikalahkan
    oleh para pejuang!
    Terima kasih atas
    partisipasimu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss7 = """
    ▬▬ FINAL BLOW! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Damage Akhir: %d
    
    🎉 NAGA HITAM MATI! 🎉
    Hadiah Koin & EXP telah
    dibagikan ke seluruh
    pejuang yg berpartisipasi!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss8 = """
    ▬▬ SERANGAN SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Damage  : %d
    Sisa HP : %d / %d (%d%%)
    Status  : [%s]
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val boss9 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    menyerang World Boss.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object buat {
    const val buat1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val buat2 = """
    ▬▬ CRAFTING (#buat) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik: #buat [id_resep]
    
    ● SENJATA (WEAPON)
    %s
    ● ARMOR / KETAHANAN
    %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val buat3 = """
    ├ %s (%s)
    │ Stat : +%d ATK
    │ Bahan: %s""";

    const val buat4 = """
    ├ %s (%s)
    │ Stat : Redam %d%%
    │ Bahan: %s""";

    const val buat5 = """
    ▬▬ RESEP NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Resep %s
    tidak ditemukan.
    Ketik #buat utk daftar.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val buat6 = """
    ▬▬ BAHAN KURANG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Untuk buat
    %s, kamu butuh:
    • %s: %d/%d unit
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val buat7 = """
    ▬▬ CRAFTING SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🎉 Kamu membuat:
    %s %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    %s""";

    const val buat8 = """
    > AUTO-EQUIP:
    %s (ATK: %d)
    Menggantikan %s.""";

    const val buat9 = """
    > Stat ATK (%d) lebih
    rendah. Masuk #inventori.""";

    const val buat10 = """
    > AUTO-EQUIP:
    %s dipasang!
    Efek : Redam %d%% damage.""";

    const val buat11 = """
    > Stat DEF (%d%%) lebih
    rendah. Masuk #inventori.
    (Saat ini: %s)""";

    const val buat12 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses pembuatan item.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object credit {
    const val credit1 = """
    ▬▬ KEHORMATAN TIM ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    [ Kepada Yth. ]
    
    ┌ 1. Syahrul
    ├ Asal : Banjarnegara
    ├ Peran: Architect/Struc
    ├ Last : 18 Aug 2019
    └ Info : Rancang fondasi,
       data & standar
      kode dari awal.
    
    ┌ 2. Aru
    ├ Asal : Banyumas
    ├ Peran: Developer/Code
    ├ Last : 23 Jun 2018
    └ Info : Nulis kode fitur
      baru sesuai desain.
    
    ┌ 3. Yahr-a
    ├ Asal : Palangkaraya
    ├ Peran: QA & Debugger
    ├ Last : 04 Aug 2019
    └ Info : Uji fitur & buru
      bug sebagai mata ke-2.
    
    ┌ 4. SAM
    ├ Asal : Buleleng
    ├ Peran: Bug Fixer
    ├ Last : 11 Jul 2021
    └ Info : Spesialis bedah
      & tuntaskan error.
    
    ┌ 5. Hika
    ├ Asal : Jakarta-B
    ├ Peran: Fullstack
    ├ Last : Ongoing
    └ Info : Backup/jembatan
      saat tim overload.
    
    ┌ 6. Yoshi
    ├ Asal : Kebumen
    ├ Peran: User Tester
    ├ Last : Sometimes
    └ Info : Coba fitur dan
      lapor ketidaksempurnaan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ⚜️ Selamat Mencoba ⚜️""";
}

object dukung {
    const val dukung1 = """
    ▬▬ DUKUNGAN SISTEM ▬▬
    
```   ___  __ ___  ___     
  // \\ || ||\\//||     
 (( ___ || || \/ ||     
  \\_|| || ||    ||     
                        
 ______  ____ __ __  __ 
 | || | ||    || // (( \
   ||   ||==  ||<<   \\ 
   ||   ||___ || \\ \_))
                        ```

    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Halo %s!
    Terima kasih sudah main
    dan mendukung project ini.
    
    ● DONASI (QRIS)
    └ yahru.pages.dev/qrcode
    
    ● MEDIA SOSIAL
    ├ IG:
    │ Ditutup (2 Okt 2026).
    ├ YT:
    │ Ditutup (2 Okt 2026).
    ├ TikTok:
    │ Ditutup (2 Okt 2026).
    └ FB:
       Ditutup (2 Okt 2026).
    
    ● INFO DONATUR
    └ Dukunganmu jadi bahan
       bakar operasional
       server, update proyek
       komunitas.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Terima kasih! ✨""";
    /*"""
        ▬▬ CLOSED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu
    tidak bisa donasi sampai
    9 Desember.
    
    Alasan:
    Sedang terjadi masalah teknis.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    """*/
}

object food {
    const val food1 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Perintah
    tidak dikenali sistem.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food3 = """
    > Karaktermu kenyang
    dan penuh energi! 😊""";

    const val food4 = """
    > Karakter mulai lapar.
    Segera beli makanan! 😐""";

    const val food5 = """
    > KONDISI KRITIS! 🚨
    Karaktermu kelaparan!""";

    const val food6 = """
    ▬▬ STATUS LAPAR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User   : %s
    Kenyang: %d / 100%%
    Status : %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #belanja untuk
    beli makanan di warung.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food7 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    cek status kelaparan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food8 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data profil
    tidak ditemukan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food9 = """
    ▬▬ WARUNG MAKAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Beli makanan untuk
    mengisi energimu:
    
    ┌ 1. Roti Gandum
    ├ Harga: 30 🪙
    ├ Efek : +25% Kenyang
    └ Ketik: #belanja roti
    
    ┌ 2. Nasi Goreng
    ├ Harga: 75 🪙
    ├ Efek : +60% Kenyang
    └ Ketik: #belanja nasgor
    
    ┌ 3. Ayam Bakar
    ├ Harga: 120 🪙
    ├ Efek : +100% Kenyang
    └ Ketik: #belanja ayam
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    AWAS: Jika lapar 0%,
    karakter MATI MUTLAK!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food10 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Karaktermu
    sedang mati! Hidupkan
    dulu sebelum makan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food11 = """
    ▬▬ FULL STOMACH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Karakter
    sudah sangat kenyang
    (100%)! Tidak perlu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food12 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Makanan tdk
    ditemukan. #belanja
    utk lihat menu resmi.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food13 = """
    ▬▬ INSUFFICIENT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koin kurang!
    %s = %d 🪙
    Koinmu: %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food14 = """
    ▬▬ MAKAN SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Membeli : %s
    Biaya   : -%d 🪙
    
    ● STATUS KENYANG
    └ %d%% ➔ %d%%
    
    Sisa 🪙 : %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val food15 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses makanan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object harian {
    const val harian1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar!
    Ketik #mulai dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val harian2 = """
    ▬▬ REWARD TERKUNCI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Klaim harian dibuka pd
    jam 19:00 - 22:00 WIB.
    
    Jam server: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Silakan kembali lagi
    nanti malam!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val harian3 = """
    ▬▬ ALREADY CLAIMED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu sudah
    ambil hadiah hari ini!
    Coba besok malam.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val harian4 = """
    ▬▬ DAILY REWARD ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Waktu Klaim: %s
    
    ● HADIAH DITERIMA
    ├ Koin : +%d
    └ EXP  : +%d
    
    ● TOTAL SAAT INI
    ├ Koin : %d
    └ EXP  : %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val harian5 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses klaim harian.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object hunger {
    const val hunger1 = """
    ▬▬ FATAL DEATH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 MATI KELAPARAN! 💀
    Energi habis total.
    
    ● HUKUMAN MUTLAK
    └ Progress DI-RESET.
      Level, EXP, EP, Koin,
      Equip & Tas HANGUS.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #mulai untuk
    daftar ulang & jangan
    pernah lupa makan lagi!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val hunger2 = """
    ▬▬ KRITIS LAPAR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ⚠️ SISA LAPAR: %d%%
    
    Segera ketik #belanja
    sblm mati & reset total!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object inventori {
    const val inventori1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val inventori2 = """
    > [AKTIF] %s (ATK:%d)""";

    const val inventori3 = """
    > [AKTIF] %s (DEF:%d%%)""";

    const val inventori4 = """
    ├ %s (ATK:%d)%s""";

    const val inventori5 = """
    ├ %s (DEF:%d%%)%s""";

    const val inventori6 = """
    ├ %s (+%d HP)%s""";

    const val inventori7 = """
    ├ %s%s""";

    const val inventori8 = """
    ├ %s %s x%d%s""";

    const val inventori9 = """
    └ (Kosong)""";

    const val inventori10 = """
    ▬▬ INVENTORI TAS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pemilik: %s
    
    ● SENJATA (WEAPON)
    %s
    ● ARMOR / DEFENSE
    %s
    ● MAKANAN (FOOD)
    %s
    ● MATERIAL & BURUAN
    %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    #pakai [nama] (Equip)
    #makan [nama] (Heal)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val inventori11 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    membuka isi inventori.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object jelajah {
    const val jelajah1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah2 = """
    ▬▬ LOW ENERGY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    EP kurang!
    Biaya   : %d EP ⚡
    EP Kamu : %d EP ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah3 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 KAMU SEDANG MATI!
    HP kamu 0/%d. Pulihkan
    atau hidupkan karakter
    sebelum menjelajah!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah4 = """
    ▬▬ AREA TERKUNCI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu masih di
    hadapan %s!
    Kalahkan dengan #serang
    sebelum lanjut jelajah!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah5 = """
    ▬▬ BOSS ENCOUNTER! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penjelajah: %s
    Sisa EP   : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ☠️ Hawa aura gelap 
    menyelimuti sekitarmu!
    Legenda %s muncul!
    
    ├ HP  : %d / %d
    └ ATK : %d Damage
    
    Ketik #serang jika berani!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah6 = """
    ▬▬ MONSTER FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penjelajah: %s
    Sisa EP   : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    👾 %s tiba-tiba
    muncul menghadang!
    
    ├ HP  : %d / %d
    └ ATK : %d Damage
    
    Ketik #serang sekarang!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah7 = """
    ▬▬ DAPAT MAKANAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penjelajah: %s
    Sisa EP   : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    📦 %s %s (+%d)
    ├ Tipe : Food
    ├ Efek : +%d HP (Heal)
    └ Tas  : %d unit
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah8 = """
    ▬▬ DAPAT MATERIAL ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penjelajah: %s
    Sisa EP   : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ✨ %s %s (+%d)
    ├ Tipe : Material
    ├ Harga: %d Koin/pcs
    └ Tas  : %d unit
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jelajah9 = """
    > AUTO-EQUIP:
    Lebih kuat dari %s
    (%d ATK). Dipasang!""";

    const val jelajah10 = """
    > MASUK TAS:
    Senjata aktif (%s -
    %d ATK) lebih kuat/setara.""";

    const val jelajah11 = """
    ▬▬ DAPAT SENJATA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penjelajah: %s
    Sisa EP   : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🗡️ %s
    ├ Tipe : Weapon
    └ ATK  : %d Damage
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    %s""";

    const val jelajah12 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses aksi jelajah.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object jual {
    const val jual1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jual2 = """
    ▬▬ PASAR (#jual) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Harga fluktuatif:
    5 - 30 Koin per unit.
    
    ● FORMAT JUAL
    ├ #jual [nama_item]
    ├ #jual [item] [jumlah]
    └ #jual [item] all
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jual3 = """
    ▬▬ EMPTY STOCK ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tidak
    punya stok %s
    di dalam tas.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jual4 = """
    ▬▬ INVALID FORMAT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Jumlah jual
    harus berupa angka atau
    ketik 'all'.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jual5 = """
    ▬▬ INVALID AMOUNT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Jumlah barang
    yang dijual harus > 0.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jual6 = """
    ▬▬ STOCK KURANG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Stok tas dari
    %s tdk cukup!
    • Stok Tas : %d unit
    • Diminta  : %d unit
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jual7 = """
    ▬▬ NOTA PENJUALAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penjual: %s
    Barang : %s
    Jumlah : %d unit
    Harga  : %d 🪙/pcs
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Total Didapat: +%d 🪙
    Sisa Stok Tas: %d unit
    Koin Sekarang: %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val jual8 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses transaksi jual.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object kode {
    const val kode1 = """
    ▬▬ FORMAT SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gunakan:
    #kode <KODE_REDEEM>
    Contoh: #kode NEWYEAR
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val kode2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val kode3 = """
    ▬▬ DB NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Belum ada
    kode redeem yg dibuat
    oleh admin saat ini.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val kode4 = """
    ▬▬ KODE INVALID ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kode:
    '%s'
    tdk ditemukan/salah!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val kode5 = """
    ▬▬ KODE EXPIRED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Masa berlaku
    kode '%s'
    sudah habis!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val kode6 = """
    ▬▬ SUDAH KLAIM ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu sudah
    pernah memakai kode:
    '%s'
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val kode7 = """
    ├ +%d Koin 🪙""";

    const val kode8 = """
    ├ +%d EXP ✨""";

    const val kode9 = """
    ├ %s (%s +%d) 📦""";

    const val kode10 = """
    ├ %s (%s) 📦""";

    const val kode11 = """
    ▬▬ KLAIM SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User : %s
    Kode : %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● HADIAH DITERIMA:
    %s▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Hadiah otomatis masuk
    ke dalam profilmu!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val kode12 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses kode redeem.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object lemparkoin {
    const val lemparkoin1 = """
    ▬▬ LEMPAR KOIN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pilih tebakanmu:
    ├ #lemparkoin angka
    └ #lemparkoin gambar
    
    ● INFO MINIGAME
    ├ Rate: 40% Win
    ├ Drop: 50-100 Koin/EXP
    └ Limit: 5x per hari
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val lemparkoin2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val lemparkoin3 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 KAMU SEDANG MATI!
    Pulihkan diri sebelum
    bermain mini game.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val lemparkoin4 = """
    ▬▬ LIMIT HABIS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    S-ARMADA GAMBLE DEP.
    
    Kesempatan main hari ini
    sudah habis! (0/%d)
    Kembali besok jam 00:00.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val lemparkoin5 = """
    🎉 TEBAKAN BENAR! 🎉
    Dapat hadiah:
    ├ +%d Koin 🪙
    └ +%d EXP ✨""";

    const val lemparkoin6 = """
    ❌ TEBAKAN SALAH! ❌
    Keberuntungan belum
    memihakmu kali ini!""";

    const val lemparkoin7 = """
    ▬▬ HASIL LEMPAR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tebakan : %s
    Koin jatuh: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sisa limit hari ini:
    [%d/%d]
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val lemparkoin8 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    proses lempar koin.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object levelup {
    const val levelup1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val levelup2 = """
    ▬▬ EXP BELUM CUKUP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Level : %d
    Status: %d / %d EXP
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kumpulkan
    %d EXP lagi dari
    mini game atau berburu
    untuk naik level!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val levelup3 = """
    ▬▬ MASS LEVEL UP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User  : %s
    Level : %d ➔ %d (+%d)
    Status: %d / %d EXP
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● TOTAL BONUS KLAIM
    ├ Koin : +%d 🪙
    └ EXP  : +%d ✨
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val levelup4 = """
    ▬▬ LEVEL UP! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User  : %s
    Level : %d
    Status: %d / %d EXP
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● BONUS KLAIM
    ├ Koin : +%d 🪙
    └ EXP  : +%d ✨
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val levelup5 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses level up.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object mw {
    const val mw1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Akun kamu
    belum terdaftar. Silakan
    buat via ketik #session !
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw2 = """
    ▬▬ TOKO TERKUNCI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🔮 MAGICAL WARE 🔮
    Hanya buka jam:
    21:00 - 22:00 WIB
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Silakan kembali nanti!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw3 = """
    ▬▬ TOKO DITUTUP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Waktu operasional usai.
    Sesi beli dibatalkan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw4 = """
    > Transaksi Magical Ware
    telah dibatalkan.""";

    const val mw5 = """
    > Kamu tdk memiliki sesi
    pembelian yg berjalan.""";

    const val mw6 = """
    ▬▬ TOKO TERKUNCI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🔮 MAGICAL WARE 🔮
    Hanya buka jam:
    21:00 - 22:00 WIB
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw7 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses Magical Ware.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw8 = """
    ▬▬ MAGICAL WARE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Buka: 21:00-22:00 WIB
    Beli: #mw <nama_item>
    
    ● TINGKAT 1 (SPESIAL)
    ├ #mw Sword Inz
    │ 3000 🪙 | +50 DMG
    ├ #mw Sword V
    │ 5500 🪙 | +80 DMG
    ├ #mw Armor Inz
    │ 3000 🪙 | +30 DEF
    ├ #mw Armor V
    │ 5500 🪙 | +40 DEF
    ├ #mw Potion XP-A
    │ 500 🪙 | +50 EXP
    ├ #mw Potion XP-B
    │ 800 🪙 | +80 EXP
    ├ #mw Potion XP-C
    │ 1000 🪙 | +100 EXP
    ├ #mw Potion XP-D
    │ 1200 🪙 | +120 EXP
    └ #mw Potion XP-V
      2800 🪙 | +300 EXP
    
    ● TINGKAT 2 (TERBATAS)
    ├ #mw Elemen Api
    │ 12k 🪙 | +200 DMG | 5j
    ├ #mw Inferno Scythe
    │ 25k 🪙 | +300 DMG |10j
    └ #mw Potion XP-Z
      8k 🪙 | +1000 EXP
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw9 = """
    ▬▬ ITEM NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Item:
    '%s'
    tdk ada di Magical Ware.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw10 = """
    ▬▬ INSUFFICIENT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koin kurang!
    Harga : %d 🪙
    Koinmu: %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw11 = """
    ▬▬ DOUBLE BUY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Batal! Kamu
    sudah memakai/punya item:
    %s
    (Tidak bisa dobel agar
    koinmu tidak terbuang).
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw12 = """
    ▬▬ KONFIRMASI MW ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Item  : %s
    Harga : %d 🪙
    Efek  : +%d %s
    %s▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Yakin beli item ini%s?
    
    ├ #mw iya
    └ #mw tidak
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw13 = """
     (Aktif %d jam)""";

    const val mw14 = """
    Aktif : %d Jam
    """;

    const val mw15 = """
    > Tidak ada transaksi
    yg menunggu konfirmasi.""";

    const val mw16 = """
    ▬▬ DATA INVALID ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data item
    Magical Ware tdk valid.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw17 = """
    ▬▬ INSUFFICIENT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koinmu tdk
    cukup untuk melunasi
    transaksi ini!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw18 = """
    > Batal: Kamu sudah
    memiliki item ini.""";

    const val mw19 = """
    ▬▬ PEMBELIAN SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Item: %s
    Biaya: -%d 🪙
    
    ✨ %d EXP instan telah
    masuk ke karaktermu!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw20 = """
    ▬▬ PEMBELIAN SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Senjata %s
    (+%d DMG) berhasil dibeli
    dan dipasang!%s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw21 = """
    ▬▬ PEMBELIAN SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Armor %s
    (+%d%% DEF) berhasil dibeli
    dan dipasang!%s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw22 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses item.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mw23 = """
    
    Aktif: %d Jam""";
}

object makan {
    const val makan1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val makan2 = """
    ▬▬ HP PENUH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    HP saat ini
    %d / %d. Tidak perlu
    makan untuk saat ini.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val makan3 = """
    ▬▬ MENU MAKAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik: #makan [nama]
    
    ● DAFTAR MAKANAN TAS
    %s▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val makan4 = """
    ├ %s %s
    │ %d buah (+%d HP)""";

    const val makan5 = """
    ▬▬ TAS KOSONG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tdk punya
    makanan/eliksir di tas.
    Cari via #jelajah atau
    beli di #toko!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val makan6 = """
    ▬▬ ITEM NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Makanan '%s'
    tdk dikenali. Pilihan:
    buah, sayur, madu, atau
    eliksir_naga.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val makan7 = """
    ▬▬ OUT OF STOCK ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Stok %s
    kamu habis! Dapatkan
    lagi via #jelajah/toko.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val makan8 = """
    ▬▬ KONSUMSI SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pengguna : %s
    Makanan  : %s %s
    Pemulihan: +%d HP
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sisa HP  : %d / %d
    Sisa %s: %d buah
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val makan9 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses perintah makan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object mancing {
    const val mancing1 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Perintah
    mancing tidak dikenal.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing2 = """
    ▬▬ TOKO PANCING SAOI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Halo! Aku Saoi. Mau beli
    peralatan baru?"
    
    ● JORAN PANCING
    ├ J. Bambu (0 🪙)
    │ Default (+0% Sukses)
    ├ J. Carbon (1000 🪙)
    │ #fish beli joran_carbon
    └ J. Titanium (5000 🪙)
      #fish beli joran_titanium
    
    ● UMPAN (10x Pancing)
    ├ Cacing (150 🪙)
    │ #fish beli umpan_cacing
    └ Udang (400 🪙)
      #fish beli umpan_udang
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Cek tas: #fishinv
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing3 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data profil
    tidak ditemukan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing4 = """
    ▬▬ SAOI (NPC) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Waduh nak, koinmu tdk
    cukup! Joran Carbon
    harganya 1.000 Koin!"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing5 = """
    ▬▬ SAOI (NPC) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Koinmu tdk cukup! Joran
    Titanium Mitos harganya
    5.000 Koin!"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing6 = """
    ▬▬ SAOI (NPC) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Umpan cacing harganya
    150 Koin, koinmu tidak
    cukup nak!"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing7 = """
    ▬▬ SAOI (NPC) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Umpan udang harganya
    400 Koin, koinmu tidak
    cukup nak!"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing8 = """
    ▬▬ SAOI (NPC) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Barang apa yg ingin
    kamu beli? Pilihan itu
    tidak tersedia!"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing9 = """
    ▬▬ SAOI (NPC) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Terima kasih sudah beli
    di tokoku! Barang sudah
    masuk perlengkapan."
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #fishinv utk cek
    tas pancingmu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing10 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses pembelian.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing11 = """
    └ -(Tas Ikan Kosong)-
    (Ketik #fishm utk mancing)""";

    const val mancing12 = """
    ├ %s : %d ekor""";

    const val mancing13 = """
    ▬▬ TAS PANCING ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● PERLENGKAPAN
    ├ Joran : %s
    ├ Cacing: %d pcs
    └ Udang : %d pcs
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● STOK TANGKAPAN IKAN
    %s▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #fishs <ikan> <jml>
    untuk menjual ikan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing14 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    membuka tas ikan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing15 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing16 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 KAMU SEDANG MATI!
    Pulihkan diri sebelum
    pergi memancing.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing17 = """
    ▬▬ BOSS TRAPPED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🌊 Kamu terperangkap
    oleh Guardian Sea!
    Fitur #fishm & #fishs
    terkunci.
    
    👉 Ketik #fisho
    untuk menyerang boss!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing18 = """
    ▬▬ SAOI (NPC) ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    "Kamu tdk punya umpan!
    Beli umpan dulu di tokoku
    dengan perintah #fish toko"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing19 = """
    ▬▬ EVENT LANGKA! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🧜‍♀️ Kailmu menarik
    seekor Putri Duyung!
    
    🌊 GUARDIAN SEA BANGKIT
    melindungi sang putri!
    Sesi mancing terhenti!
    
    👉 Ketik #fisho
    untuk serang Guardian!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing20 = """
    ▬▬ MANCING GAGAL ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🌊 Umpanmu dimakan ikan
    kecil/tersangkut karang!
    (Umpan terpakai -1)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing21 = """
    ▬▬ MANCING SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu menangkap:
    %s %s
    
    Estimasi Jual: %d 🪙
    Total di Tas : %d ekor
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing22 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses memancing.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing23 = """
    ▬▬ LOCKED ACTION ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Fitur jual
    terkunci karena kamu sdg
    melawan Guardian Sea!
    Serang dgn #fisho
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing24 = """
    ▬▬ PASAR IKAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Format Jual Ikan:
    ├ #fishs <ikan>
    └ #fishs <ikan> <jml>
    
    Contoh: #fishs kakap 2
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing25 = """
    ▬▬ INVALID AMOUNT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Jumlah ikan
    yg dijual harus > 0!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing26 = """
    ▬▬ EMPTY STOCK ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Batal! Kamu
    belum pernah menangkap
    ikan apa pun.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing27 = """
    ▬▬ FISH NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Batal! Kamu
    tidak punya ikan '%s'
    di dalam tas!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing28 = """
    ▬▬ STOCK KURANG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Stok %s
    kamu tdk cukup!
    Punya: %d | Jual: %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing29 = """
    ▬▬ JUAL IKAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Terjual: %dx %s
    Dapat  : +%d 🪙
    Sisa   : %d ekor
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing30 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses jual ikan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing31 = """
    ▬▬ NO ENCOUNTER ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu sedang
    tdk berhadapan dengan
    Guardian Sea!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing32 = """
    ▬▬ GUARDIAN TEWAS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🌊 Guardian Sea hancur!
    Putri Duyung kabur ke
    dasar samudra.
    
    ● REWARD MYTHIC!
    ├ 🐲 1x Naga Laut
    ├ 🦈 1x Hiu Purba
    ├ 🐉 1x Leviathan Muda
    └ 🔮 1x Mutiara Laut
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing33 = """
    ▬▬ KAMU TEWAS! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🌊 Ombak Guardian Sea
    memberi %d DMG!
    Kamu terhempas & mati!
    
    Sesi boss berakhir tragis.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing34 = """
    ▬▬ BATTLE LOG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ⚔️ Seranganmu: %d DMG
    🌊 Balasan Boss: %d DMG
    
    HP Boss: %d / %d
    HP Kamu: %d / %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mancing35 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    bertarung dgn boss laut.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object matematika {
    const val matematika1 = """
    ▬▬ FORMAT SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gunakan:
    #<cmd> <angka1> <angka2>
    
    Contoh:
    ├ #tambah 10 30
    ├ #kurang 5 10
    ├ #kali 2 2
    └ #bagi 2 1
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val matematika2 = """
    ▬▬ CALC ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Angka
    pembagi tidak boleh 0!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val matematika3 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Perintah
    tidak dikenali.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val matematika4 = """
    ▬▬ KALKULATOR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Hitung: %s %s %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Hasil %s = %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val matematika5 = """
    ▬▬ INPUT INVALID ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pastikan
    kamu memasukkan angka
    yang valid!
    (Contoh: #tambah 10 30)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object menu {
    const val menu1 = """
    ▬▬ Menu Utama ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Masuki Dunia Petualangan!
    Ketik #cmd sekarang.
    
    ● [ 👤 UTAMA & AKUN ]
    ├ #menu
    ├ #bantuan
    ├ #mulai
    ├ #profil
    ├ #levelup
    ├ #rank
    ├ #kode
    ├ #credit
    ├ #version
    ├ #rules
    ├ #runtime
    ├ #donasi
    ├ #vip
    ├ #s̶l̶
    └ #ping
    
    ● [ ⚔️ ACTION RPG ]
    ├ #jelajah
    ├ #berburu
    ├ #serang
    ├ #tambang
    ├ #tebang
    ├ #buat
    └ Buktikan ketangguhanmu!
    
    ● [ 🎒 EKONOMI & ITEM ]
    ├ #inventori
    ├ #pakai
    ├ #makan
    ├ #beli
    ├ #jual
    ├ #tf
    ├ #slot
    ├ #lemparkoin
    └ Jadilah yang terkaya!
    
    ● [ 🍖 KONSUMSI ]
    ├ #belanja
    ├ #konsumsi
    └ Jangan sampai mati!
    
    ● [ 🎲 MINI GAMES ]
    ├ #caklontong
    ├ #tekateki
    ├ #susunkata
    ├ #asahotak
    ├ #tebakkata
    ├ #tebaklirik
    ├ #siapakahaku
    ├ #tebakbendera
    ├ #tebakkimia
    ├ #tebaknegara
    ├ #tebakkallimat
    └ Uji wawasanmu!
    
    ● [ 📐 MATH & LOGIC ]
    ├ #mtk
    ├ #tambah
    ├ #kurang
    ├ #bagi
    ├ #kali
    └ Cari hasilnya!
    
    ● [ 🗡️ BATTLE & PvP ]
    ├ #pvpm
    ├ #pvps
    ├ #pvpn
    ├ #raidm
    ├ #raids
    └ Bantai lawanmu!
    
    ● [ 🌾 BERTANI ]
    ├ #tanam
    ├ #siram
    ├ #panen
    └ Ayo menanam!
    
    ● [ 🎣 FISHING ]
    ├ #fishm
    ├ #fishs
    ├ #fisho
    ├ #fishinv
    ├ #fish toko
    ├ #fish beli
    └ Tarik kailmu!
    
    ● [ ⏰ SPECIAL EVENT ]
    ├ #bos
    ├ #serangbos
    ├ #mw
    ├ #bank
    ├ #daily
    ├ #rampok
    └ Rebut kejayaan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object mtkgame {
    const val mtkgame1 = """
    ▬▬ MTK MASIH AKTIF ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tingkat : %s
    Soal    : %s
    Waktu   : %d detik
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Jawab: #mtk jawab <angka>
    Atau : #mtk nyerah
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame2 = """
    ▬▬ TANTANGAN MTK ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pembuat : %s
    Tingkat : %s
    Batas   : 5 Menit
    Hadiah  : +%d 🪙 | +%d ✨
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ❓ SOAL: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik: #mtk jawab <angka>
    (Siapapun boleh menjawab!)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame3 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal memulai
    game matematika.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame4 = """
    ▬▬ GAME NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada game
    MTK yang berlangsung saat
    ini.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame5 = """
    ▬▬ WAKTU HABIS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Batas 5 menit berakhir!
    Sesi ditutup otomatis.
    
    💡 Jawaban benar: %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame6 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Hanya %s
    (pembuat game) yang
    dapat menyerah!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame7 = """
    ▬▬ PLAYER NYERAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    %s
    telah menyerah!
    
    💡 Jawaban benar: %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame8 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal memproses
    penyerahan game.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame9 = """
    ▬▬ GAME NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada game
    MTK aktif. Ketik:
    #mtk mulai <tingkat>
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame10 = """
    ▬▬ SESI KADALUARSA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Game MTK ini sudah lewat
    dari batas waktu 5 menit.
    
    💡 Jawaban benar: %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame11 = """
    ▬▬ INVALID FORMAT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Jawaban harus
    berupa angka bulat valid!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame12 = """
    ▬▬ JAWABAN SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Masih salah!
    Silakan coba lagi,
    %s!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame13 = """
    ▬▬ JAWABAN BENAR! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pemenang: %s
    Tingkat : %s
    Jawaban : %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● HADIAH DITERIMA
    ├ Koin : +%d 🪙
    └ EXP  : +%d ✨
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame14 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memeriksa jawaban.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame15 = """
    ▬▬ FORMAT SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik format:
    #mtk jawab <angka>
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mtkgame16 = """
    ▬▬ MINI GAME MTK ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Uji ketajaman logikamu!
    
    ● DAFTAR PERINTAH
    ├ #mtk mulai <tingkat>
    │ └ easy/medium/hard/
    │   nightmare
    ├ #mtk jawab <angka>
    │ └ Umum boleh jawab!
    └ #mtk nyerah
      └ Khusus pembuat
    
    ⏱️ Batas Waktu: 5 Menit
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Contoh: #mtk mulai hard
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object mulai {
    const val mulai1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mulai2 = """
    ▬▬ AKSES DITERIMA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Akun kamu
    sudah aktif sebelumnya!
    Kamu bebas menggunakan
    semua perintah profil.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mulai3 = """
    ▬▬ PENDAFTARAN SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Selamat! Kamu menyetujui
    dan mengaktifkan akses.
    
    ● FITUR TERBUKA:
    ├ #profil
    ├ #slot
    ├ #caklontong
    ├ #susunkata
    └ Dan game hiburan lain!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #menu untuk mulai!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val mulai4 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses pendaftaran.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object pakai {
    const val pakai1 = """
    ▬▬ FORMAT SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gunakan:
    #pakai <NAMA_ITEM>
    
    Contoh:
    #pakai Pedang Cyber
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pakai2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pakai3 = """
    ▬▬ TAS KOSONG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tidak
    punya item apa pun di
    dalam tas inventori!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pakai4 = """
    ▬▬ ITEM NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Item:
    '%s'
    tdk ditemukan di tasmu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pakai5 = """
    ▬▬ INVALID TYPE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Batal dipakai!
    Item %s
    berjenis %s.
    
    Hanya bisa equip item
    bertipe Weapon / Armor!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pakai6 = """

    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    > %s masuk ke tas.""";

    const val pakai7 = """
    ▬▬ EQUIP SENJATA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User   : %s
    Aktif  : %s
    Damage : %d ATK%s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pakai8 = """
    ▬▬ EQUIP ARMOR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User   : %s
    Aktif  : %s
    Reduksi: %d%% DEF%s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pakai9 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses equip item.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object ping {
    const val ping1 = """
    ├ Grup  : %s""";

    const val ping2 = """
    ├ Jalur : Privat (PM)""";

    const val ping3 = """
    ▬▬ SYSTEM PONG! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pengirim: %s
    %s
    Respon  : %d ms
    Waktu   : %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Status  : Online 🟢
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object profil {
    const val profil1 = """
    ▬▬ GROUP ONLY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Fitur #profil
    hanya bisa digunakan di
    dalam grup.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val profil2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data profil
    belum ada. Daftar
    via ketik #session dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val profil3 = """
    ▬▬ PROFIL MEGAFINAL ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sesi: %s
    
    ● IDENTITAS EKSKLUSIF
    ├ ID   : #%d
    ├ Nama : %s
    ├ VIP  : 🥇 MEGAFINAL
    └ Aktif: %s
    
    ● STATUS KARAKTER
    ├ Lvl  : %d
    ├ EXP  : %d / %d
    ├ EP   : %d ⚡
    ├ HP   : %d / %d
    └ Koin : %d 🪙
    
    ● EQUIP TEMPUR
    ├ Senjata:
    │ %s (+%d ATK)
    └ Armor:
      %s (+%d%% DEF)
    
    ● HAK ISTIMEWA
    ├ A̶i̶̶  : Dihapus
    ├ C̶h̶a̶t̶ : Dihapus
    └ Bonus Harian: 
      +400EXP | +200EP |
      +100🪙  | +5 Level
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ⚜️ Selamat mencoba ⚜️""";

    const val profil4 = """
    ▬▬ PROFIL VIP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sesi: %s
    
    ● IDENTITAS
    ├ ID   : #%d
    ├ Nama : %s
    ├ VIP  : %s
    └ Aktif: s.d. %s
    
    ● STATUS KARAKTER
    ├ Lvl  : %d
    ├ EXP  : %d / %d
    ├ EP   : %d ⚡
    ├ HP   : %d / %d
    └ Koin : %d 🪙
    
    ● EQUIP TEMPUR
    ├ Wep: %s (+%d ATK)
    └ Def: %s (+%d%% DEF)
    
    ● MANFAAT HARI INI
    ├ C̶H̶A̶T̶ ̶A̶I̶: Dihapus
    └ P̶e̶r̶s̶o̶n̶a̶: Dihapus
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val profil5 = """
    ▬▬ PROFIL PENGGUNA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● IDENTITAS
    ├ ID    : #%d
    ├ Nama  : %s
    └ Status: Reguler
    
    ● STATUS KARAKTER
    ├ Lvl  : %d
    ├ EXP  : %d / %d
    ├ EP   : %d ⚡
    ├ HP   : %d / %d
    └ Koin : %d 🪙
    
    ● EQUIP TEMPUR
    ├ Wep: %s (+%d ATK)
    └ Def: %s (+%d%% DEF)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #vip utk info VIP.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val profil6 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses data profil.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object pvp {
    const val pvp1 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Perintah
    PvP tidak dikenal.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp3 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 Kamu sedang mati!
    Hidupkan karaktermu
    sebelum bertarung.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp4 = """
    ▬▬ LEVEL KURANG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Minimal Level
    5 untuk bertarung PvP.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp5 = """
    ▬▬ LOW ENERGY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    EP kurang!
    Minimal butuh 10 EP
    untuk duel PvP.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp6 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada
    tantangan PvP yang
    ditujukan padamu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp7 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data lawan
    tidak ditemukan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp8 = """
    ▬▬ DUEL DIMULAI! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tantangan diterima!
    Kalian terkunci di
    sesi pertarungan.
    
    ├ #pvps : Menyerang
    └ #pvpn : Menyerah
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp9 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada
    tantangan PvP untuk
    ditolak.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp10 = """
    > Tantangan PvP ditolak.""";

    const val pvp11 = """
    ▬▬ PANDUAN PVP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ├ #pvpm <ID_Lawan>
    │ Menantang pemain.
    └ #pvpm terima / tolak
      Merespons tantangan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp12 = """
    ▬▬ STATUS BUSY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu sedang
    dlm sesi PvP/tantangan
    aktif lainnya!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp13 = """
    ▬▬ DB ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Database
    indeks user belum ada.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp14 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User dengan
    ID %s tdk ditemukan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp15 = """
    ▬▬ INVALID TARGET ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tidak
    bisa menantang dirimu
    sendiri!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp16 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data profil
    lawan tidak ditemukan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp17 = """
    ▬▬ TARGET DEAD ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Lawan sedang
    dalam keadaan mati!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp18 = """
    ▬▬ TARGET LOW LVL ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Lawan belum
    mencapai Level 5!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp19 = """
    ▬▬ TARGET LOW EP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Lawan tdk
    memiliki cukup EP
    (Minimal 10 EP).
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp20 = """
    ▬▬ TARGET BUSY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Lawan sedang
    bertarung/menanggapi
    tantangan lain!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp21 = """
    ▬▬ TANTANGAN PVP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pemain @%s
    ditantang oleh @%s!
    
    👉 Lawan harus jawab:
    ├ #pvpm terima
    └ #pvpm tolak
    (Batas: 5 menit)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp22 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses tantangan PvP.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp23 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data profil
    tidak ditemukan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp24 = """
    ▬▬ NOT IN BATTLE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tidak
    sedang berada di dalam
    pertarungan PvP!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp25 = """
    ▬▬ BATTLE CANCELED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Lawan tdk
    ditemukan, duel batal.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp26 = """
    ├ Ambil: %d Koin lawan
    │ (Bonus +%d Koin)""";

    const val pvp27 = """
    ├ Ambil: %d EXP lawan
    │ (Bonus +%d EXP)""";

    const val pvp28 = """
    ▬▬ FATAL STRIKE! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu memberi %d DMG!
    ☠️ LAWAN TELAH GUGUR!
    
    🏆 KEMENANGAN MUTLAK!
    %s
    ├ Bonus +15 EP
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp29 = """
    ▬▬ SERANGAN PVP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu menyerang lawan
    dan memberi %d DMG!
    
    🩸 HP Lawan: %d / %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp30 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    melakukan serangan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp31 = """
    ▬▬ NOT IN BATTLE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tidak
    berada di sesi PvP!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp32 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data lawan
    tidak ditemukan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp33 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada
    ajakan damai yg perlu
    disetujui.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp34 = """
    ▬▬ DAMAI DISEPAKATI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kedua pihak sepakat!
    Sesi PvP dihentikan
    secara damai.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp35 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada
    ajakan damai untuk
    ditolak.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp36 = """
    ▬▬ DAMAI DITOLAK ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Lawan menolak untuk
    menyerah/damai!
    Pertarungan berlanjut!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp37 = """
    ▬▬ AJUKAN DAMAI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu mengajukan damai
    kepada lawan.
    👉 Lawan harus ketik:
    ├ #pvpn setuju
    └ #pvpn tolak
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val pvp38 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses perdamaian.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object raid {
    const val raid1 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Perintah
    Raid tidak dikenal.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar! Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid3 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 KAMU SEDANG MATI!
    Pulihkan HP kamu sebelum
    memulai Raid Battle.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid4 = """
    ▬▬ RAID ONGOING ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu masih
    dlm sesi Raid melawan:
    %s (HP: %d)
    
    Ketik #raids untuk
    melanjutkan serangan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid5 = """
    ▬▬ RAID BATTLE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🔥 SESI RAID DIMULAI! 🔥
    
    ● BOSS TERPILIH
    ├ Makhluk : %s
    ├ HP Boss : %d
    ├ Base DMG: %d
    └ Drop    : %s
    
    ● KEMAMPUAN KHUSUS
    %s▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    👉 Ketik #raids untuk
    mengerahkan seranganmu!
    Bantai boss sekarang!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid6 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal mencari
    musuh Raid saat ini.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid7 = """
    ▬▬ NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Data profil
    tidak ditemukan!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid8 = """
    ▬▬ NO RAID ACTIVE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tidak
    berada dalam sesi Raid!
    Ketik #raidm utk cari
    lawan boss.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid9 = """
    ▬▬ RAID FAILED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 Karaktermu tewas!
    Sesi Raid otomatis
    dibatalkan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid10 = """
    melempar Bola Kematian, 
    muncul di depan matamu & 
    memenggal kepalamu instan!""";

    const val raid11 = """
    muncul di belakangmu & 
    mencabut jantungmu secara 
    brutal!""";

    const val raid12 = """
    menghancurkan tubuhmu & 
    melempar sisa ragamu ke 
    pusat Matahari!""";

    const val raid13 = """
    ▬▬ FATAL ANOMALY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    BATTLE TAK SEIMBANG
    Anos Voldigoad %s
    
    💥 DMG   : ∞ (INFINITY)
    ☠️ Status: KAMU TEWAS
       SEKETIKA DI TEMPAT!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid14 = """
    > 💨 SERANGAN MISS!
    %s
    berhasil menahan atau
    menghindar dari tebasanmu!
    """;

    const val raid15 = """
    > ⚔️ HIT! Kamu menyerang
    %s
    sebesar %d DMG!
    """;

    const val raid16 = """
    ▬▬ RAID VICTORY! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🎉 MAKHLUK TUMBANG! 🎉
    Kamu memenangkan Raid
    melawan: %s
    
    ● REWARD EKSKLUSIF
    ├ Item : %s
    ├ Koin : +%d 🪙
    └ EXP  : +%d ✨
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kerja bagus, Penjelajah!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid17 = " 💥 (Tombak Ekstra +20% DMG)";

    const val raid18 = " 🧪 (Gigitan Terkutuk! ATK -30%)";

    const val raid19 = " 🌋 (Magma Tembus DEF! -20% HP)";

    const val raid20 = " 🐉 (Nafas Naga! -10% HP)";

    const val raid21 = " 🔥 (Bola Api! -10% HP)";

    const val raid22 = " 🪨 (Batu Beruntun! Extra DMG)";

    const val raid23 = " 👑 (Pukulan Bayangan Demoniac)";

    const val raid24 = " 🔮 (Sihir Api Elaina!)";

    const val raid25 = " 🔮 (Sihir Air Elaina!)";

    const val raid26 = " 🔮 (Bola Sihir Raksasa!)";

    const val raid27 = " 🔮 (Lemparan Tomat & Sayur!)";

    const val raid28 = " 🔮 (Lemparan Tong TNT!)";

    const val raid29 = " 🪵 (Lemparan Kayu Runcing!)";

    const val raid30 = """
    %s> ☠️ %s
    menyerang balik! (%d DMG)%s
    
    💥 Status:
    KAMU TEWAS DALAM RAID!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid31 = """
    %s> 👹 %s
    menyerang balik! (%d DMG)%s
    
    🩸 HP Boss: %d / %d
    ❤️ HP Kamu: %d / %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val raid32 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    menyerang dalam Raid.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object rampok {
    const val rampok1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar! Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rampok2 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 KAMU SEDANG MATI!
    Karaktermu blm bangkit.
    Kunjungi Kuil Kebangkitan
    terlebih dahulu!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rampok3 = """
    ▬▬ WAKTU TIDAK TEPAT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🚫 AREA TERLALU SEPI
    Aksi merampok hanya buka
    pada jam: 07:00-14:00.
    
    Di luar jam tsb, suasana
    sepi & penjagaan ketat!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rampok4 = """
    ├ Hukuman: Koin -30%%
    │ (-%d 🪙)""";

    const val rampok5 = """
    ├ Hukuman: EXP -20%%
    │ (-%d ✨)""";

    const val rampok6 = """
    ▬▬ DIHAKIMI MASSA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User : %s
    Batas: Ke-%d (Maks 6/hr)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 TERTANGKAP WARGA!
    Kamu tewas dihajar massa
    karena nekat merampok
    melebihi batas!
    
    %s
    💡 Gunakan #kuil untuk
    bangkit kembali.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rampok7 = """
    ▬▬ RAMPOK SUKSES! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pelaku : %s
    Sisa Hr: %d/6
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🗡️ Kamu lolos!
    ● HASIL JARAHAN
    ├ Koin : +%d 🪙
    └ EXP  : +%d ✨
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rampok8 = """
    ▬▬ RAMPOK GAGAL! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pelaku : %s
    Sisa Hr: %d/6
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🚨 TERCIDUK!
    Kamu terpergok warga
    dan melarikan diri, tapi
    menjatuhkan %d Koin! 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rampok9 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses aksi rampok.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object rank {
    const val rank1 = """
    ▬▬ DB NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Database
    pengguna belum ditemukan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rank2 = """
    ▬▬ NO DATA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Belum ada
    data pengguna yang
    telah terdaftar.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rank3 = """
    ▬▬ NO PLAYERS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Belum ada
    data pemain untuk bisa
    ditampilkan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val rank4 = "👑 Supreme Leader";

    const val rank5 = "⚔️ High General";

    const val rank6 = "🛡️ Elite Knight";

    const val rank7 = "📜 Adventurer";

    const val rank8 = """
    %s #%d %s
    ├ %s
    └ Lv.%d | 🪙 %d | ✨ %d
    """;

    const val rank9 = """
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    📍 POSISI KAMU:
    Peringkat #%d dari %d
    Lv.%d | 🪙 %d | ✨ %d""";

    const val rank10 = """
    ▬▬ GLOBAL RANKING ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    %s%s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object runtime {
    const val runtime1 = """
    ▬▬ SYSTEM RUNTIME ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ⏱️ Runtime  : %s
    📡 Host : %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🟢 Status  : Online
    💬 Response: One-to-one
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object serang {
    const val serang1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val serang2 = """
    ▬▬ DEATH STATE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💀 KAMU SEDANG MATI!
    HP kamu 0/%d.
    Pulihkan dirimu sebelum
    bertarung kembali.
    
    (Gunakan #bangkitkan
    Helper, bukan
    Proj utama)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val serang3 = """
    ▬▬ NO ENEMY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada
    monster di hadapanmu
    saat ini untuk diserang.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val serang4 = """
    
    > DUPLIKAT SPESIAL:
    Kamu sdh pernah dapat
    Drop Spesial dari %s!
    Diganti: +%d 🪙 | +%d ✨""";

    const val serang5 = """
    
    > LEGENDARY DROP (WEP):
    Dapat: %s (%d ATK)
    AUTO-EQUIP: Lebih kuat!
    Otomatis dipasang.""";

    const val serang6 = """
    
    > LEGENDARY DROP (WEP):
    Dapat: %s (%d ATK)
    MASUK TAS: Senjata aktif
    masih lebih kuat/setara.""";

    const val serang7 = """
    
    > LEGENDARY DROP (DEF):
    Dapat: %s (%d%% DEF)
    AUTO-EQUIP: Lebih kuat!
    Otomatis dipasang.""";

    const val serang8 = """
    
    > LEGENDARY DROP (DEF):
    Dapat: %s (%d%% DEF)
    MASUK TAS: Armor aktif
    masih lebih kuat/setara.""";

    const val serang9 = """
    ▬▬ VICTORY! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💥 Hit: %s
    (Pakai %s)
    Dmg : %d Damage
    
    🎉 %s TUMBANG!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● REWARD BATTLE
    ├ Koin : +%d 🪙
    └ EXP  : +%d ✨%s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val serang10 = "\n└ (Diredam %d%% oleh %s)";

    const val serang11 = "\n└ Koin terkuras (-%d 🪙)";

    const val serang12 = "\n└ Koin selamat (%d 🪙)";

    const val serang13 = """
    ▬▬ DEFEAT! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    💥 Hit: %s
    Dmg : %d Damage
    
    🩸 Balasan dari %s:
    Dmg : %d Damage!%s
    
    💀 KAMU TEWAS!
    Monster melarikan diri!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🧚‍♀️ KINEY MUNCUL:
    %s
    %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val serang14 = "\n└ (Reduksi %d%% Armor)";

    const val serang15 = """
    ▬▬ BATTLE LOG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🥊 Hit : -%d HP
       (Pakai %s)
    🩸 Dmg : -%d HP dari %s%s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    👾 HP %s : %d / %d
    ❤️ HP Kamu : %d / %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik #serang lagi!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val serang16 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses pertempuran.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object slot {
    const val slot1 = """
    ▬▬ GROUP ONLY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Game #slot
    hanya bisa dimainkan
    di dalam grup!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val slot2 = """
    ▬▬ LOW ENERGY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    EP kurang!
    Butuh minimal %d EP ⚡
    EP kamu saat ini: %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val slot3 = """
    ▬▬ INVALID FORMAT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Taruhan koin
    harus berupa angka!
    (Contoh: #slot 20)
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val slot4 = """
    ▬▬ INVALID AMOUNT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Jumlah koin
    taruhan harus > 0!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val slot5 = """
    ▬▬ INSUFFICIENT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koin kurang!
    Koin kamu saat ini:
    %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val slot6 = "🎉 SUPER JACKPOT! 🎉";

    const val slot7 = "🎰 JACKPOT! 🎰";

    const val slot8 = "✨ MENANG SEDANG! ✨";

    const val slot9 = "💥 KAMU KALAH! 💥";

    const val slot10 = "├ Hadiah: +%d 🪙\n";

    const val slot11 = "├ Rugi  : -%d 🪙\n";

    const val slot12 = """
    ▬▬ SLOT MACHINE ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    User: %s
    
    ┌── [ %s | %s | %s ] ──┐
    
    %s
    %s├ Biaya : -%d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sisa 🪙: %d
    Sisa ⚡: %d
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val slot13 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal sistem
    saat memutar mesin slot.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object syalina {
    const val syalina1 = """
    ▬▬ SYALINA AI CHAT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik : #sl <pesan>
    Contoh: #sl Hai!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    (😆) Syalina:
    "Ayo bicara denganku!
    Ada petualangan apa
    hari ini? ✨"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val syalina2 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val syalina3 = """
    ▬▬ LIMIT HABIS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    (😳) Syalina:
    "Maaf sayang... kuota
    chat kita (%d/%d) sudah
    habis! Tunggu besok ya,
    aku akan merindukanmu!❤️"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val syalina4 = """
    ▬▬ LIMIT HABIS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    (😾) Syalina:
    "Ehh! Kuota chat kamu
    denganku sudah habis!
    (%d/%d)
    Kembalilah besok ya!"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val syalina5 = """
    ▬▬ SYALINA AMD ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sisa Kuota: %d / %d
    Status VIP: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val syalina6 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    (😾) Syalina:
    "Ih! Sihir pesanku
    mendadak error! Coba
    lagi nanti ya!"
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object tambang {
    const val tambang1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val tambang2 = """
    ▬▬ LOW ENERGY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    EP kurang!
    Biaya   : %d EP ⚡
    EP Kamu : %d EP ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val tambang3 = """
    ├ %s %s: +%d (Tot: %d)""";

    const val tambang4 = """
    ▬▬ HASIL MENAMBANG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penambang: %s
    Biaya    : -5 EP
    Sisa EP  : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● MATERIAL DIPEROLEH
    %s▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val tambang5 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses aksi tambang.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object tebang {
    const val tebang1 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val tebang2 = """
    ▬▬ LOW ENERGY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    EP kurang!
    Biaya   : %d EP ⚡
    EP Kamu : %d EP ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val tebang3 = """
    ▬▬ HASIL MENEBANG ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penebang: %s
    Biaya   : -3 EP
    Sisa EP : %d ⚡
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● KAYU DIPEROLEH
    ├ Dapat : +%d Kayu 🪵
    └ Di Tas: %d Kayu
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val tebang4 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses aksi tebang.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object transfer {
    const val transfer1 = """
    ▬▬ FORMAT SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gunakan:
    #tf <id_target> <jumlah>
    
    Contoh:
    #tf 102 500
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer2 = """
    ▬▬ INVALID AMOUNT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Nominal tf
    harus lebih dari 0 koin!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer3 = """
    ▬▬ LIMIT TRANSFER ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Maksimal tf
    sekali transaksi adalah
    1.000.000 koin!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer4 = """
    ▬▬ INVALID FORMAT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ID Target
    dan Nominal harus
    berupa angka valid!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer5 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Akun kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer6 = """
    ▬▬ INVALID TARGET ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Kamu tidak
    bisa transfer koin
    ke diri sendiri!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer7 = """
    ▬▬ INSUFFICIENT ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koin kurang!
    Koinmu saat ini: %d 🪙
    Nominal transfer: %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer8 = """
    ▬▬ ID NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ID Target
    '%d' tdk ditemukan
    di index database!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer9 = """
    ▬▬ LIMIT PENERIMA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Koin milik
    penerima sudah mencapai
    batas maksimal!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer10 = """
    ▬▬ TRANSFER SUKSES ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Penerima ID: %d
    Nominal    : %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sisa Koin Kamu: %d 🪙
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val transfer11 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal saat
    memproses transfer koin.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object triviagame {
    const val triviagame1 = """
    ▬▬ GROUP ONLY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Game %s
    hanya bisa dimainkan
    di dalam grup!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame2 = """
    ▬▬ PANDUAN KUIS ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Game: %s
    
    ├ %s mulai
    │ (Biaya 1 EP ⚡)
    ├ %s jawab <tebakan>
    └ %s nyerah
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame3 = """
    ▬▬ GAME AKTIF ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Masih ada game %s
    yang sedang berjalan!
    
    ❓ Soal: %s
    💡 Hint: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame4 = """
    ▬▬ ACCESS DENIED ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Profil kamu
    belum terdaftar. Silakan
    daftar lebih dulu.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame5 = """
    ▬▬ LOW ENERGY ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    EP kurang!
    Butuh minimal 1 EP ⚡
    untuk memulai game.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame6 = """
    ▬▬ DB NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Database
    soal '%s'
    tidak ada di sistem.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame7 = """
    ▬▬ NO DATA ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Belum ada
    soal di database %s.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame8 = """
    ▬▬ KUIS DIMULAI ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Game  : %s
    Mulai : %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ❓ Soal: %s
    💡 Hint: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Ketik: %s jawab <isi>
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame9 = """
    ▬▬ SYSTEM ERROR ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Gagal baca
    database soal %s.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame10 = """
    ▬▬ GAME NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada
    game %s aktif.
    Ketik %s mulai
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame11 = """
    ▬▬ FORMAT SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Sertakan
    jawabanmu!
    Contoh: %s jawab <isi>
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame12 = """
    ▬▬ JAWABAN BENAR! ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pemenang: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    🎯 Jawab: %s
    🗣️ Info : %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● HADIAH DITERIMA
    ├ Koin : +%d 🪙
    └ EXP  : +%d ✨
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame13 = """
    ▬▬ JAWABAN SALAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Masih salah!
    Silakan coba lagi.
    
    💡 Hint: %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame14 = """
    ▬▬ GAME NOT FOUND ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Tidak ada
    permainan %s
    yang sedang berjalan.
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

    const val triviagame15 = """
    ▬▬ PLAYER NYERAH ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ❓ Soal : %s
    🎯 Jawab: %s
    🗣️ Info : %s
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object updatets {
    const val updatets1 = """
    ▬▬ CATATAN PEMBARUAN ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    
    ● 27 Des 17 | V-0.0.1-b
    ├ Connection Chain
    ├ Configuration
    └ Adding ping (test)
    
    ● 18 Jan 18 | V-1.1.0-s
    ├ Adding menu & level
    ├ Update connection
    ├ Fix configuration
    └ Adding jelajah
    
    ● 14 Mar 18 | V-1.2.0
    ├ System regis & login
    ├ Profil by ID & gen
    └ Auth & session sec.
    
    ● 08 Jun 18 | V-1.3.5
    ├ Combat (#serang) & DMG
    ├ Kiney NPC & loot sys
    └ Adding status kondisi
    
    ● 19 Nov 18 | V-1.5.0
    ├ Relog Protocol
    ├ In-memory cache
    └ Cek grup & user
    
    ● 24 Feb 19 | V-1.8.0
    ├ Redeem Code system
    ├ Syalina Check
    └ Admin Gift feature
    
    ● 10 Aug 19 | V-2.0.0-m
    ├ Update #levelup & #ping
    └ #warning (Rules)
    
    ● 15 Jan 20 | V-2.2.0
    ├ Expand #jelajah loot
    ├ #credit (Developer)
    └ Auto-equip logic
    
    ● 08 Jun 20 | V-2.5.0
    └ Support Helper
    
    ● 12 Feb 21 | V-2.7.5
    ├ Kuil Kebangkitan (Help)
    └ Helper PM Isolation
    
    ● 18 Nov 21 | V-2.9.0
    ├ Opt. Of Response (OOR)
    └ Fix bug 010111001001011010
    
    ● 20 Mei 22 | V-3.0.0
    ├ Special Monster (10%)
    ├ 5 Special Monsters
    └ Boss Preset System
    
    ● 14 Nov 22 | V-3.2.0
    ├ Exc Boss Drop Anti-Dup
    ├ Auto-equip Boss tier
    └ Dup Drop ➔ Koin/EXP
    
    ● 10 Mar 23 | V-3.5.0-s
    ├ Compatibility update
    ├ Codebase cleanup
    └ Final RPG Polish
    
    ● 17 Jul 25 | V-4.0.0-f
    ├ Update code
    └ Fix profil & tas
    
    ● 29 Aug 26 | V-4.0.5-u
    ├ MW, bank, tf, daily
    ├ tebang, tambang, slot
    ├ Mass levelup all
    ├ Fix Malbolge bug
    └ Update credit (Yoshi)
    
    ● 06 Sep 26 | V-5.0.5-t
    ├ Lemparkoin, PvP, Rampok
    ├ Game MTK
    └ Fix Runtime & Syalina
    
    ● 19 Sep 26 | V-5.1.0
    ├ Add/Fix Raid
    ├ New features
    └ Fix Malbolge #OX@O7&Z&&...
    
    ● 19 Sep 26 | V-5.1.5
    ├ 23:43 Night.
    ├ Change B appearance
    ├ Word adjustments such as
    │ "dengan" to "dgn"
    └ Fix Profile Error D#^8... 
    
    ● 4 Okt 26 | V-5.2.0-t
    ├ Delete system Password.
    ├ Delete system chat pm.
    ├ Public claim established.
    ├ Delete Syalina Feature.
    ├ Delete Masukkan Feature.
    ├ Group only established.
    ├ Discount Vip,
    │  (Not exciting).
    └ Update code MU.
    
    ● 5 Okt 26 | V-5.2.5
    ├ Adding tanam, siram,
    │ panen.
    └ Fix profile error.
    
    ● 17 Okt 26 | V-5.3.0-short
    ▬ Re-change Properti.
    ▭ Shorten Cmd.
    ▬ Delete 48.473 data Usr.
    ▭ Re-credit (Pension).
    
    ● 18 Okt 26 | V-5.3.5-fix
    ▬ Fix Jelajah.
    ▭ Fix Tambang.

    ● 1 Nov 26 | V-5.4.0-update
    ▬ Add system guard.
    ▭ Access with number.
    ▬ Add #intro command.
    ▭ Add id [name (id)].
    
    ● 2 Nov 26 | V-5.4.5-fix
    ▬ Fix Database Error Read.
    ▭ Add + Fix #aktifkan cmd.
    
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ⚜️ Selamat Mencoba ⚜️""";
}

object vip {
    const val vip1 = """
    ▬▬ LAYANAN VIP ▬▬
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Berlangganan VIP untuk
    percepat petualanganmu!
    
    ● 🥉 TIER: PRO
    ├ 50 EP / hari
    ├ 100 EXP / hari
    ├ C̶h̶a̶t̶ ̶A̶I̶ ̶5̶0̶x̶ ̶/̶ ̶h̶a̶r̶i̶
    │
    ├ 5̶K̶ 1K  ➔ 2 Hari
    ├ 1̶0̶K̶ 2K ➔ 4 Hari
    └ 1̶5̶K̶ 5K ➔ 7 Hari
    
    ● 🥈 TIER: PLUSPRO
    ├ 150 EP / hari
    ├ 300 EXP / hari
    ├ C̶h̶a̶t̶ ̶A̶I̶ ̶7̶0̶x̶ ̶/̶ ̶h̶a̶r̶i̶
    ├ Level Up +2 / hari
    │
    ├ 1̶7̶K̶ 8K ➔ 2 Hari
    ├ 2̶0̶K̶ 10K ➔ 5 Hari
    └ 2̶5̶K̶ 12K ➔ 7 Hari
    
    ● 🥇 TIER: MEGAFINAL
    ├ 200 EP / hari
    ├ 400 EXP / hari
    ├ 100 Koin / hari
    ├ C̶h̶a̶t̶ ̶A̶I̶ ̶1̶0̶0̶x̶ ̶/̶ ̶h̶a̶r̶i̶
    ├ Level Up +5 / hari
    ├ A̶I̶ ̶N̶o̶ ̶C̶e̶n̶s̶o̶r̶e̶d̶
    ├ Wep Custom (100 DMG)
    ├ Def Custom (100 DEF)
    ├ 1x Kode Custom
    │
    ├ 3̶0̶K̶ 15K ➔ 4 Hari
    ├ 4̶0̶K̶ 17K ➔ 7 Hari
    └ 5̶0̶K̶̶ 20K ➔ 10 Hari
    
    
    ● 📬 HUBUNGI & BELI
    ├ (Sertakan ID Usermu!)
    ├ TG: Ditutup
    ├ TT: Ditutup
    ├ IG: Ditutup
    ├ WA: Ditutup
    └ FB: Ditutup
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Pesan akan dibalas oleh
    Customer Service
    pada jam aktif:
    07:00 - 22:00
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object warning {
    const val warning1 = """
    ▬▬ ATURAN & WARNING ▬▬
    
┏━━━┳━S━┓┏┓┏━━━┓
┃┏━┓┃┏I┓┣┛┃┃┏━┓┃
┗┛┏┛┃┃M┃┣┓┃┃┗━┛┃
┏━┛┏┫┃P┃┃┃┃┃┏━┓┃
┃┃┗━┫┗E┛┣┛┗┫┗━┛┃
┗━━━┻━L━B━━O━━━T
                   
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    ● 1. DILARANG SPAM
    ├ Jangan ketik perintah
    ├ berturut-turut dalam
    ├ waktu yang singkat.
    └ Akibat: B delay/lag.
    
    ● 2. BIJAK BERMAIN
    ├ B murni utk hiburan.
    ├ Jika bosan/tdk cocok,
    ├ bisa berhenti kapan
    └ saja tanpa konflik.
    
    ● 3. SARAN FITUR
    ├ Terbuka utk ide baru.
    ├ Prioritaskan ide game
    ├ yang simpel & ringan
    └ agar performa terjaga.
    
    ● 4. PANDUAN MANDIRI
    ├ Bingung pakai fitur?
    ├ Ketik perintah utama
    ├ tanpa angka/nama.
    ├ Cth: #beli atau #bank
    └ untuk lihat petunjuk.
    
    ● 5. B
    ├ Data, dan sistem B?
    ├ Sistem One-to-one.
    └ Data public claim.
    
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭
    Mari jaga kenyamanan dan
    ketertiban kita bersama!
    ▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object commandmanager {
const val commandmanager1 = """
▬▬ WORLD BOSS AKTIF ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
⚔️ EVENT BERLANGSUNG!
Semua fitur game biasa
dikunci sementara agar
fokus bertarung!

👉 Ketik #serangbos
untuk mengeroyok boss
bersama-sama!
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val commandmanager2 = """
▬▬ NO WORLD BOSS ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Tidak ada
event World Boss saat
ini. 

Buka setiap hari jam
17:00 - 18:00 WIB (atau
sampai boss mati).
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val commandmanager3 = """
▬▬ PVP TERKUNCI ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Kamu sedang
di sesi/tantangan PvP!
Fitur lain dikunci.

● CMD DIIZINKAN
├ #pvpm terima / tolak
├ #pvps (Serang)
└ #pvpn (Nyerah/Damai)
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val commandmanager4 = """
▬▬ FITUR TERKUNCI ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Kamu harus
menyetujui & aktifkan
akses B lebih dulu.

Ketik #mulai untuk buka
semua fitur & profil.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object tsp {
const val tsp1 = """
▬▬ SYSTEM ERROR ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Perintah tdk
dikenali sistem.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp2 = """
▬▬ ACCESS DENIED ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Profil kamu
belum terdaftar! Silakan
daftar lebih dulu.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp3 = """
▬▬ DEATH STATE ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
💀 KAMU SEDANG MATI!
Pulihkan dirimu sebelum
mulai bercocok tanam.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp4 = """
▬▬ LADANG TERISI ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Tanaman : %s
Disiram : %d / %d kali
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
👉 Selesaikan penyiraman
dgn #siram dan panen dgn
#panen sblm tanam lagi.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp5 = """
▬▬ KATALOG PERTANIAN ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Pilih bibit yang ingin
kamu tanam di ladang:

""";

const val tsp6 = """
├ %s (#tanam %s)
│ Butuh %dx Siram
└ Hasil: %dx %s
""";

const val tsp7 = """
▬▬ TANAM BERHASIL ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Kamu menanam %s
di ladangmu!

💧 Butuh %d kali
penyiraman hingga matang.

👉 Ketik #siram untuk
menyiram (CD: 5 menit).
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp8 = """
▬▬ SYSTEM ERROR ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Gagal sistem
saat memproses #tanam.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp9 = """
▬▬ NOT FOUND ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Data profil
tidak ditemukan!
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp10 = """
▬▬ DEATH STATE ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
💀 KAMU SEDANG MATI!
Pulihkan dirimu sebelum
menyiram ladang.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp11 = """
▬▬ LADANG KOSONG ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Ladangmu
masih kosong! Ketik
#tanam untuk menanam.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp12 = """
▬▬ SIAP PANEN! ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
✨ %s
sudah cukup disiram
dan SIAP DIPANEN!

👉 Ketik #panen untuk
mengambil hasilnya!
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp13 = """
▬▬ COOLDOWN SIRAM ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Tanaman
%s masih basah!

⏳ Tunggu %d menit
%d detik lagi untuk
menyiram kembali.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp14 = """
▬▬ SIRAM BERHASIL ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Menyiram %s!
Progress: %d / %d kali
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
🎉 TANAMAN MATANG
SEMPURNA!
👉 Ketik #panen sekarang
utk memanen hasilnya!
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp15 = """
▬▬ SIRAM BERHASIL ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Menyiram %s!
Progress: %d / %d kali
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
⏳ Silakan siram kembali
dalam 5 menit ke depan.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp16 = """
▬▬ SYSTEM ERROR ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Gagal sistem
saat menyiram tanaman.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp17 = """
▬▬ DEATH STATE ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
💀 KAMU SEDANG MATI!
Pulihkan dirimu sebelum
memanen tanaman.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp18 = """
▬▬ BELUM ADA HASIL ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Tidak ada
tanaman yg bisa dipanen!
Ketik #tanam.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp19 = """
▬▬ BELUM MATANG ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
%s
belum siap dipanen!
💧 Disiram: %d / %d
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
👉 Gunakan #siram sampai
cukup penyiraman.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp20 = """
▬▬ PANEN BERHASIL ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Memanen %s (Food)
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
😋 Kamu langsung makan
hasil panen tsb!
(Kenyangan +%d%%)

🔋 Kenyangan Saat Ini:
%d%%
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp21 = """
▬▬ PANEN BERHASIL ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Memanen %s!
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
📦 Hasil Masuk ke Tas:
+%dx %s
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";

const val tsp22 = """
▬▬ SYSTEM ERROR ▬▬
▬▭▬▭▬▭▬▭▬▭▬▭▬▭
Gagal sistem
saat memanen tanaman.
▬▭▬▭▬▭▬▭▬▭▬▭▬▭""";
}

object intro {
    const val intro1 = """
 ࣭₊˚ʚ ᗢ₊˚✧ ﾟ. ₊˚ʚ ᗢ₊˚✧ ﾟ. ₊˚ʚ ᗢ₊˚✧ ﾟ. 𝙒𝙀𝙇𝘾𝙊𝙈𝙀🥳 ₊˚ʚ ᗢ₊˚✧ ﾟ. ₊˚ʚ ᗢ₊˚✧ ﾟ. ₊˚ʚ ᗢ₊˚✧ ﾟ.
𓊔 NAMA :
𓊔 GENDER :
𓊔 KELAS :
𓊔 UMUR :
𓊔 ASAL :
𓊔 HOBI :
ʬʬʬ ʬʬʬ ʬʬʬ 𝑺𝑨𝑳𝑨𝑴 𝑲𝑬𝑵𝑨𝑳 ʬʬʬ ʬʬʬ ʬʬʬ""";
}
}
