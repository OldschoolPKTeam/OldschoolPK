#!/bin/bash

builds=`find "$PWD/game/plugins" -name '*.gradle' -or -name '*.gradle.kts'`
for file in $builds; do
	( #SubShell
		fullPath=$(dirname $file)
		directoryName="${fullPath##*/}"
		fileName="$(basename $file)"
		
		cd $fullPath
		if [ $directoryName != "template" ] && [ -d "src" ]; then
			if [[ $fullPath =~ game\/plugins\/.+ ]]; then
				# Create private remote, push code, add as submodule
				relativeDir="${BASH_REMATCH[0]}/"
				echo "$directoryName is a plugin module [relative=\"$relativeDir\"] [path=\"$fullPath\"]"
			else
				echo "No relative submodule directory found for $fullPath"
			fi
		fi
	)
done
