package com.project.barbearia.services.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UniqueDataChecker() {

    fun verifyBusinessCode(code: String): Boolean{
        var res: Boolean = false
        val scope: Job = CoroutineScope(Dispatchers.Unconfined).launch {
            if(code.length == 14 && code.contains(Regex("[0-9]"))){
                res = true
            }
        }

        scope.start()
        return if(scope.isCompleted){
            res
        } else {
            res
        }
    }
}