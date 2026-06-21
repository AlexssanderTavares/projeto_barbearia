package com.example.projeto_barbearia.services.utils.verifiers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostalCodePatternVerifier : PatternVerifier {

    override val patterns: Set<Regex> = setOf(Regex("[\\d+\\-]"))

    override fun verify(code: String?): Boolean {
        var res: Boolean = false
        val scope: Job = CoroutineScope(Dispatchers.Unconfined).launch {
            if (patterns.elementAt(0).matches(code!!) && code.length == 9 || code.length == 8) {
                res = true
            } else {
                res = false
            }
        }

        if(code != null){
            scope.start()
        }

        return when {
            scope.isCompleted -> res
            else -> false
        }
    }
}