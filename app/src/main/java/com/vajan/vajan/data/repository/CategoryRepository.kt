package com.vajan.vajan.data.repository

import com.vajan.vajan.data.Api
import com.vajan.vajan.data.offline.database.dao.BhajanDao
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.toRecords
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val dao: BhajanDao
) {


    fun getCategory() = dao.getCategory()
    fun getRecordByCategory(category:String) = dao.getRecordByCategory(category)


}
