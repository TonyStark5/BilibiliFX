@file:Suppress("SqlNoDataSourceInspection")

package ink.bluecloud.service.clientservice.database.data

import ink.bluecloud.service.clientservice.database.DataService
import ink.bluecloud.service.provider.InjectListOnly
import org.ktorm.database.Database

@InjectListOnly
class ConnectToDataBase: DataService() {

    fun connect(userName: String,password: String) = IO {
        Database.connect(
            url = "jdbc:h2:file:./config/database/bilifx-${userName};AUTO_SERVER=TRUE",
            driver = "org.h2.Driver",
            user = userName,
            password = password
        ).run {
            database.set(this)
            checkTables(this)
        }
    }

    private fun checkTables(database: Database) {
        database.useConnection {
            it.prepareStatement(userinfoTables).executeQuery()
        }
    }

    private val userinfoTables = """
                    CREATE TABLE IF NOT EXISTS user_info(
                        name varchar(16) not null,
                        mid varchar(32) primary key not null,
                        cookie text not null,
                        fans int not null,
                        followers int not null,
                        signature int not null,
                        head varbinary(500) not null
                    )
                """.trimIndent()
}