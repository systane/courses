package com.strider.posterrbff.listener.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class PostRequest (
    @field:NotBlank(message = "the post message must not be blank")
    @field:Size(max = 177, message = "the message must have at the maximum 177 characters")
    val message: String
)