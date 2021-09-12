package src.main.kotlin.ru.sber.io

import java.io.*
import java.util.zip.*

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(inputFileName: String, zipFileName: String) {
        FileInputStream(inputFileName).use { input ->
            ZipOutputStream(FileOutputStream(zipFileName)).use { output ->
                val buff = ByteArray(input.available())
                val entry = ZipEntry(inputFileName)
                output.putNextEntry(entry)
                input.read(buff)
                output.write(buff)
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(zipFileName: String, originFileName: String) {
        val buff = ByteArray(1024)
        ZipInputStream(FileInputStream(zipFileName)).use { input ->
            if (input.nextEntry != null) {
                FileOutputStream(originFileName).use { output ->
                    var tmp = input.read(buff)
                    while (tmp != -1) {
                        output.write(buff, 0, tmp)
                        tmp = input.read(buff)
                    }
                    input.closeEntry()
                }
            }
        }
    }
}