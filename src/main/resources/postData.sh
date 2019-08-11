curl -i -X POST  http://$1/demo-ws/v1/params -H 'Content-Type: application/json'  -d '{
 "key1": "value1",
 "key2": "value2",
 "MoreParams": {
    "key3": "value3",
    "key4": "value4"
 },
 "ttl": "60",
 "date": "'"$(date +%Y-%m-%dT%H:%M:%S)"'"
}'



