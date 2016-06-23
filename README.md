# apisense-stings

Sting implementations for the APISENSE platform

## GSMSting

```javascript
var log = require('log');
var gsm = require('gsm');

log.info('networkOperatorName: ' + gsm.networkOperatorName());
log.info('networkType: ' + gsm.networkType());
log.info('signalStrengthLevel: ' + gsm.signalStrengthLevel());
log.info('dbm: ' + gsm.dbm());
log.info('asu: ' + gsm.asu());
```
