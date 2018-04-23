#!/bin/bash

while read p; do
 IFS=', ' read -r -a array <<< $p

   csvloader "${array[@]}" -p NewLoan_ML_SP 
done <test.csv

