package dev.fakedata.ui.main.fragments.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.fakedata.App
import dev.fakedata.bo.Users
import dev.fakedata.bo.UsersAPIOptions
import dev.fakedata.model.UserInfo
import javax.inject.Inject

class UsersViewModel @Inject constructor(private val users: Users) : ViewModel() {

    fun getUsersFromLocalDB(options: UsersAPIOptions): LiveData<PagedList<UserInfo>> {
        return LivePagedListBuilder(users.getUsersFromLocalDB(options), options.pageSize).setBoundaryCallback(object : PagedList.BoundaryCallback<UserInfo>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                users.getUsersFromServer(options)
            }

            override fun onItemAtEndLoaded(itemAtEnd: UserInfo) {
                super.onItemAtEndLoaded(itemAtEnd)
            }

            override fun onItemAtFrontLoaded(itemAtFront: UserInfo) {
                super.onItemAtFrontLoaded(itemAtFront)
            }
        }).build()
    }

}
