package com.example.prueba_ceiba.data.dataSource.cloud

enum class Http(val status: Int) {
    OK (200),
    NOT_CONNECTION(0),
    BAD_REQUEST (400)
}