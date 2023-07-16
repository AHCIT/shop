local statusAndCount = {}
local likeStatus = 'info:like:status:'
local likeCount = 'info:like:count:'
local collectStatus = 'info:collect:status:'
local collectCount = 'info:collect:count:'
local transmitStatus = 'info:transmit:status:'
local transmitCount = 'info:transmit:count:'
local scanStatus = 'info:scan:status:'
local scanCount = 'info:scan:count:'
statusAndCount['likeStatus'] = tonumber(redis.call('HGET', likeStatus .. KEYS[1], KEYS[2]))
statusAndCount['likeCount'] = tonumber(redis.call('GET', likeCount .. KEYS[2]))
statusAndCount['collectStatus'] = tonumber(redis.call('HGET', collectStatus .. KEYS[1], KEYS[2]))
statusAndCount['collectCount'] = tonumber(redis.call('GET', collectCount .. KEYS[2]))
statusAndCount['transmitStatus'] = tonumber(redis.call('HGET', transmitStatus .. KEYS[1], KEYS[2]))
statusAndCount['transmitCount'] = tonumber(redis.call('GET', transmitCount .. KEYS[2]))
statusAndCount['scanStatus'] = tonumber(redis.call('HGET', scanStatus .. KEYS[1], KEYS[2]))
statusAndCount['scanCount'] = tonumber(redis.call('GET', scanCount .. KEYS[2]))
return cjson.encode(statusAndCount)
