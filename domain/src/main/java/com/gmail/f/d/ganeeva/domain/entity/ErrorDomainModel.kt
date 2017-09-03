package com.gmail.f.d.ganeeva.domain.entity

/**
 * Type for catching errors returned by backendless
 * @param code - error code provided by backendless
 * @param message - error message provided by backendless
 */
class ErrorDomainModel( override var message:String, var code: Int) : DomainModel, Exception() {
    //
}