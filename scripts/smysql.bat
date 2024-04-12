::
:: Start/Stop MYSQL SERVER
::
@echo OFF
set OPT=%1
if %OPT%. == start. (
	docker run -d --name mysql --rm -p3307:3306 ^
			-v "D:\Ecoles\EPSI\2023-2024\B3_B\Data":/var/lib/mysql -e MYSQL_ROOT_PASSWORD=epsi2024 mysql:8.2
) else if %OPT%. == stop. (
	@echo Stop Docker Mysql 
	docker stop mysql
) else (
	goto HELP 
)

exit /B 

:HELP 
@echo. %0% ^[start^|stop^]
@echo. Start OR Stop Docker Container Mysql 
@echo. 