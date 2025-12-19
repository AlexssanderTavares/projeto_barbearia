package com.project.barbearia.services.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UniqueDataChecker() {

    fun verifyEmail(email: String): Boolean {
        var res: Boolean = false
        val scope: Job = CoroutineScope(Dispatchers.Unconfined).launch {
            if(email.contains("@") && email.contains(".com")){
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

    fun verifyPass(pass: String): Boolean {
        var res: Boolean =  false
        val scope: Job = CoroutineScope(Dispatchers.Unconfined).launch {
            if(pass.length >= 8 && pass.contains(Regex("[^a-zA-Z0-9\\s]")) && pass.contains(Regex("[a-zA-Z0-9]"))){
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

    fun verifyPostalCode(code: String): Boolean{
        var res: Boolean = false
        val scope: Job = CoroutineScope(Dispatchers.Unconfined).launch {
            if(code.length == 8 && code.contains(Regex("[0-9]"))){
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