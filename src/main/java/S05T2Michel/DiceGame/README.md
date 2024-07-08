# Dice Game REST API Services

Dice Game related services for IT Academy Final Project

_(Java & Spring Framework bootcamp)_

## Postman collection

<details open>
<summary>Open and copy! Ready to import & consume: </summary>
<br>
<pre>{
	"info": {
		"_postman_id": "3b97b8e4-b3f4-4636-8d1c-85ebc39c0b97",
		"name": "Dice Game Rest API",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "35577418"
	},
	"item": [
		{
			"name": "AuthenticationController",
			"item": [
				{
					"name": "Sign Up",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"playerName\":\"hola\",\r\n    \"password\":\"123\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{url}}/signup",
							"host": [
								"{{url}}"
							],
							"path": [
								"signup"
							]
						}
					},
					"response": []
				},
				{
					"name": "Sign In",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"playerName\":\"admin\",\r\n    \"password\":\"admin\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{url}}/signin",
							"host": [
								"{{url}}"
							],
							"path": [
								"signin"
							]
						}
					},
					"response": []
				}
			],
			"auth": {
				"type": "noauth"
			},
			"event": [
				{
					"listen": "prerequest",
					"script": {
						"type": "text/javascript",
						"packages": {},
						"exec": [
							""
						]
					}
				},
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"packages": {},
						"exec": [
							""
						]
					}
				}
			]
		},
		{
			"name": "GameController",
			"item": [
				{
					"name": "New Game",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "pruebaActualizada",
							"options": {
								"raw": {
									"language": "text"
								}
							}
						},
						"url": {
							"raw": "{{url}}2{{games}}",
							"host": [
								"{{url}}2{{games}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get All Games",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "pruebaActualizada",
							"options": {
								"raw": {
									"language": "text"
								}
							}
						},
						"url": {
							"raw": "{{url}}2{{games}}",
							"host": [
								"{{url}}2{{games}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "Delete All Games",
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "pruebaActualizada",
							"options": {
								"raw": {
									"language": "text"
								}
							}
						},
						"url": {
							"raw": "{{url}}2{{games}}",
							"host": [
								"{{url}}2{{games}}"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "PlayerController",
			"item": [
				{
					"name": "Update  PlayerName",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "ivan",
							"options": {
								"raw": {
									"language": "text"
								}
							}
						},
						"url": {
							"raw": "{{url}}/update-name/9",
							"host": [
								"{{url}}"
							],
							"path": [
								"update-name",
								"9"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Player",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{url}}/get/2",
							"host": [
								"{{url}}"
							],
							"path": [
								"get",
								"2"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get All Players",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{url}}/",
							"host": [
								"{{url}}"
							],
							"path": [
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "WinRate",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{url}}/winrate",
							"host": [
								"{{url}}"
							],
							"path": [
								"winrate"
							]
						}
					},
					"response": []
				},
				{
					"name": "Best Player",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{url}}/winrate/winner",
							"host": [
								"{{url}}"
							],
							"path": [
								"winrate",
								"winner"
							]
						}
					},
					"response": []
				},
				{
					"name": "Worst Player",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{url}}/winrate/loser",
							"host": [
								"{{url}}"
							],
							"path": [
								"winrate",
								"loser"
							]
						}
					},
					"response": []
				},
				{
					"name": "Delete Player",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "{{url}}/delete/10",
							"host": [
								"{{url}}"
							],
							"path": [
								"delete",
								"10"
							]
						}
					},
					"response": []
				}
			]
		}
	],
	"auth": {
		"type": "bearer"
	},
	"event": [
		{
			"listen": "prerequest",
			"script": {
				"type": "text/javascript",
				"packages": {},
				"exec": [
					""
				]
			}
		},
		{
			"listen": "test",
			"script": {
				"type": "text/javascript",
				"packages": {},
				"exec": [
					""
				]
			}
		}
	],
	"variable": [
		{
			"key": "url",
			"value": "http://localhost:8080/auth",
			"type": "string"
		}
	]
}</pre>
</details>

## Built with 🛠️

* [Spring Security](https://spring.io/projects/spring-security/) - Automated access control (authentication and authorization)
* [JSON Web Token (JWT)](https://jwt.io/) - Secure access tokens for authentication
* [Maven](https://maven.apache.org/) - Dependency management tool
* [Swagger](https://swagger.io/) - Documenting and consuming RESTful web services
* [MySQL](https://www.mysql.com/) & [MongoDB](https://www.mongodb.com/) - Diversifying persistence with both relational and non-relational databases for diverse data storage needs

## Author ✒️

* **I. Michel** - *Junior Java Developer* - [GitHub](https://github.com/I-Michel)

---