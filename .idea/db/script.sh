#! /bin/sh
mongoimport -d spotify -c viral50 --authenticationDatabase admin -u rootuser -p rootpass --type csv --file Globalviral50.csv --headerline
mongoimport -d spotify -c viral50 --authenticationDatabase admin -u rootuser -p rootpass --type csv --file Polandviral50.csv --headerline
mongoimport -d spotify -c top200 --authenticationDatabase admin -u rootuser -p rootpass --type csv --file Globaltop200.csv --headerline
mongoimport -d spotify -c top200 --authenticationDatabase admin -u rootuser -p rootpass --type csv --file Polandtop200.csv --headerline
mongoimport --db spotify --collection roles --authenticationDatabase admin --username rootuser --password rootpass --file roles.json --jsonArray