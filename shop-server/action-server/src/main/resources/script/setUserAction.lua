redis.call('HSET', KEYS[1], ARGV[1], ARGV[2])
local status = tonumber(ARGV[2])
if status == 1 then
    redis.call('INCR', KEYS[2])
else
    redis.call("DECR", KEYS[2])
end