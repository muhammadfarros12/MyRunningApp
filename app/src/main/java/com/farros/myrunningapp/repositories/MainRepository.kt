package com.farros.myrunningapp.repositories

import com.farros.myrunningapp.db.Run
import com.farros.myrunningapp.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDAO
) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunSortedByDate() = runDao.getAllRunSortedByDate()

    fun getAllRunSortedByDistance() = runDao.getAllRunSortedByDistance()

    fun getAllRunSortedByTimeInMillis() = runDao.getAllRunSortedByTimeMillis()

    fun getAllRunSortedByAvgSpeed() = runDao.getAllRunSortedByAvgSpeed()

    fun getAllRunSortedByCaloriesBurned() = runDao.getAllRunSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalInMillis() = runDao.getTotalTimeInMillis()

}