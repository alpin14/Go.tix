package org.d3if3007.gotix.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3007.gotix.db.GotixDao

class HistoryViewModel (db: GotixDao) : ViewModel() {
    val data = db.getLastTiket()
}