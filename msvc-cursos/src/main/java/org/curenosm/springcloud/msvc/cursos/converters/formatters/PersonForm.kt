package org.curenosm.springcloud.msvc.cursos.converters.formatters

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class PersonForm(
    @get:NotNull @get:Size(max = 64)
    private val name: String,
    @get:Min(0)
    private val age: Int,
    // Using custom annotation contraint for validation purposes
    @get:MyConstraint
    private val field: String
)