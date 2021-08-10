# BACKEND FOR SUMMITWORKS PROJ 1

## contributers: Aryan & Steven

### ROOT CREDENTIALS ARE

####email: `admin`
####password: `password`

### NOTE: this is not an actual stored user account, it's a pseudo-admin that should be used to create real admin accounts

We save credentials for a session in an httpOnly cookie called `ngo_authtoken`. it's made by encoding into base64: `username:password`

so if you'd like to use postman to connect to the api, you can give yourself a cookie with credentials (root for example)

`admin:password ===(base64encoding)===> YWRtaW46cGFzc3dvcmQ=`

and then define your cookie as such:
`ngo_authtoken=YWRtaW46cGFzc3dvcmQ=; Path=/; Expires=Tue, 09 Aug 2022 22:45:23 GMT;`

then you should be free to access most of the endpoints.