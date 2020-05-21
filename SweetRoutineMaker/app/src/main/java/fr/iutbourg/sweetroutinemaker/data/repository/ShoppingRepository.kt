package fr.iutbourg.sweetroutinemaker.data.repository


private class ShoppingRepositoryImpl : ShoppingRepository {

}

interface ShoppingRepository {

    companion object {
        val shoppingInstance : ShoppingRepository by lazy {
            ShoppingRepositoryImpl()
        }
    }
}
