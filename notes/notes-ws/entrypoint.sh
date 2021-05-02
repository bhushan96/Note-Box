#!/bin/bash
entrypoint_params=$1

if [ $1 == "copy" ];
then
	printf "==> INITAITED COPY RESOURCES PROCESS\n"
	mkdir -p /app/resources
	cp -r /app/application.yml /app/resources
	cp -r /app/ddl_scripts/* /app/resources/
fi
printf "==> RESOURCES COPY PROCESS COMPLETED\n"

printf "==> APPLICATION START PROCESS INITAITED \n"

java -cp app:app/lib/* com.project.notes.NotesApplication