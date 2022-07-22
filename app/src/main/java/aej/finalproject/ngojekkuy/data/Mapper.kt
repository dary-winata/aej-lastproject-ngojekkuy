package aej.finalproject.ngojekkuy.data

object Mapper {
    fun mapUserResponse(userResponse: UserResponse?):List<User>{
        val mapper: (UserResponse.Data) -> User = {
            User(
                id = it.id,
                name = it.name,
                username = it.username
            )
        }

        return userResponse?.data?.map(mapper).orEmpty()
    }

    fun mapLoginResponse(loginResponse: LoginResponse?): String = loginResponse?.data?.token.orEmpty()
}