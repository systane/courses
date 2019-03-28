status_code=$(awk 'BEGIN {"curl -sI http://localhost:9090/config-file/default" | getline; print $2}')

if [ "$status_code" -eq 200 ] ; then
  exit 0;
else
  exit 1;
fi