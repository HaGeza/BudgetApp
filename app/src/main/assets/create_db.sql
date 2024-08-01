CREATE TABLE IF NOT EXISTS accounts (`name` TEXT NOT NULL, `balance` TEXT NOT NULL, `currency` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);

CREATE TABLE IF NOT EXISTS exchange_rates (`source` TEXT NOT NULL, `other` TEXT NOT NULL, `rate` REAL NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);

CREATE UNIQUE INDEX IF NOT EXISTS `index_exchange_rates_source_other` ON exchange_rates (`source`, `other`);

INSERT INTO exchange_rates (source, other, rate) VALUES
('USD', 'USD', 1),
('EUR', 'USD', 1.0808594296167404),
('GBP', 'USD', 1.282835681986569),
('INR', 'USD', 0.011945482024615478),
('AUD', 'USD', 0.6535407952822819),
('CAD', 'USD', 0.7220905363154709),
('SGD', 'USD', 0.7442584956281133),
('CHF', 'USD', 1.1294746707075805),
('MYR', 'USD', 0.21617547870865947),
('JPY', 'USD', 0.006504068402113431),
('CNY', 'USD', 0.13790965466074423),
('NZD', 'USD', 0.5896845662813246),
('THB', 'USD', 0.027807787443929192),
('HUF', 'USD', 0.0027343847707153087),
('AED', 'USD', 0.27229407760381213),
('HKD', 'USD', 0.12799184624428206),
('MXN', 'USD', 0.053184582020882006),
('ZAR', 'USD', 0.054471755419042964),
('PHP', 'USD', 0.017068954039984135),
('SEK', 'USD', 0.09266990924387246),
('IDR', 'USD', 0.00006127529452229728),
('BRL', 'USD', 0.17686295045573247),
('SAR', 'USD', 0.26666666666666666),
('TRY', 'USD', 0.030226261112013902),
('KES', 'USD', 0.007663729583192907),
('KRW', 'USD', 0.0007218940791697754),
('EGP', 'USD', 0.02064272968367732),
('IQD', 'USD', 0.0007632026607606563),
('NOK', 'USD', 0.09110367317872275),
('KWD', 'USD', 3.270419819667835),
('RUB', 'USD', 0.011397613998479795),
('DKK', 'USD', 0.14483844598849338),
('PKR', 'USD', 0.0035905725841968166),
('ILS', 'USD', 0.26682335353604236),
('PLN', 'USD', 0.2518966236656208),
('QAR', 'USD', 0.2747252747252747),
('XAU', 'USD', 2385.2645550237357),
('OMR', 'USD', 2.5973704781532674),
('COP', 'USD', 0.00024492516555955647),
('CLP', 'USD', 0.0010443100301093138),
('TWD', 'USD', 0.030403751113502506),
('ARS', 'USD', 0.0010732615775386524),
('CZK', 'USD', 0.0425032094759636),
('VND', 'USD', 0.000039814053467148964),
('MAD', 'USD', 0.10087438034688727),
('JOD', 'USD', 1.4104372355430184),
('BHD', 'USD', 2.6595744680851063),
('XOF', 'USD', 0.0016477595781686002),
('LKR', 'USD', 0.0033068800855529396),
('UAH', 'USD', 0.024360434401492832),
('NGN', 'USD', 0.0006047957485907834),
('TND', 'USD', 0.32229457194019273),
('UGX', 'USD', 0.00026882469553241316),
('RON', 'USD', 0.217208489687132),
('BDT', 'USD', 0.008509464796623602),
('PEN', 'USD', 0.26765471177832817),
('GEL', 'USD', 0.36711495870683314),
('FJD', 'USD', 0.44219603109054884),
('VES', 'USD', 0.02735072771640374),
('BYN', 'USD', 0.3058134286123495),
('UZS', 'USD', 0.00008007027308855382),
('BGN', 'USD', 0.5526346510774149),
('DZD', 'USD', 0.00741702044290872),
('IRR', 'USD', 0.00002373897845421907),
('DOP', 'USD', 0.016829733524860557),
('ISK', 'USD', 0.007229758395356043),
('CRC', 'USD', 0.0018961360575601327),
('XAG', 'USD', 27.978426615968694),
('SYP', 'USD', 0.00007691214785546999),
('JMD', 'USD', 0.0063870472220077254),
('LYD', 'USD', 0.20662431701824513),
('GHS', 'USD', 0.06438031444975373),
('MUR', 'USD', 0.02144775170253744),
('AOA', 'USD', 0.0011264855387689283),
('UYU', 'USD', 0.024817894973853522),
('AFN', 'USD', 0.014097799997242599),
('LBP', 'USD', 0.000011020669808570402),
('XPF', 'USD', 0.009057602020221437),
('TTD', 'USD', 0.14726643241274162),
('TZS', 'USD', 0.00036964404919862984),
('ALL', 'USD', 0.010780883354059315),
('XCD', 'USD', 0.37013268326861776),
('GTQ', 'USD', 0.1289817211063998),
('NPR', 'USD', 0.007462428252141484),
('BOB', 'USD', 0.14466028351437268),
('BBD', 'USD', 0.5),
('LAK', 'USD', 0.000045121845547376084),
('BND', 'USD', 0.7442584956281133),
('BWP', 'USD', 0.07349736932442462),
('HNL', 'USD', 0.040333408222810924),
('PYG', 'USD', 0.00013199947537106287),
('ETB', 'USD', 0.01734041620943299),
('NAD', 'USD', 0.054471755419042964),
('PGK', 'USD', 0.25710117881327704),
('SDG', 'USD', 0.0016681020914607312),
('MOP', 'USD', 0.12426392839250684),
('BMD', 'USD', 1),
('NIO', 'USD', 0.027145610457880692),
('BAM', 'USD', 0.5526346510774149),
('KZT', 'USD', 0.0021099258727436607),
('PAB', 'USD', 1),
('GYD', 'USD', 0.004784254328095134),
('YER', 'USD', 0.003997237636349372),
('MGA', 'USD', 0.0002191954121989219),
('KYD', 'USD', 1.217242946377812),
('MZN', 'USD', 0.015649835423297946),
('RSD', 'USD', 0.009232620070720784),
('SCR', 'USD', 0.07366243042138945),
('AMD', 'USD', 0.0025898785678700955),
('AZN', 'USD', 0.5879706533038532),
('SBD', 'USD', 0.1203099208689218),
('TOP', 'USD', 0.4181227777948245),
('BZD', 'USD', 0.493990724208035),
('GMD', 'USD', 0.014681139085089013),
('MWK', 'USD', 0.0005760315073499003),
('BIF', 'USD', 0.0003475069350135885),
('HTG', 'USD', 0.00756774847777165),
('SOS', 'USD', 0.0017549919972116234),
('GNF', 'USD', 0.00011600343797028616),
('MNT', 'USD', 0.000294312521130478),
('MVR', 'USD', 0.06485334837012936),
('CDF', 'USD', 0.00035229025325300586),
('STN', 'USD', 0.0440825367015703),
('TJS', 'USD', 0.09448257599515134),
('KPW', 'USD', 0.001111097508266795),
('KGS', 'USD', 0.011900594040286365),
('LRD', 'USD', 0.00511787606133603),
('LSL', 'USD', 0.054471755419042964),
('MMK', 'USD', 0.00047658419381683547),
('GIP', 'USD', 1.282835681986569),
('XPT', 'USD', 956.6060647008901),
('MDL', 'USD', 0.05636104630112483),
('CUP', 'USD', 0.041686957615321395),
('KHR', 'USD', 0.00024381494500132157),
('MKD', 'USD', 0.017559978430667332),
('VUV', 'USD', 0.008341791571165984),
('ANG', 'USD', 0.5579259511318546),
('MRU', 'USD', 0.0252523990420492),
('SZL', 'USD', 0.054471755419042964),
('CVE', 'USD', 0.009801935518425144),
('SRD', 'USD', 0.03449042311238136),
('SVC', 'USD', 0.11428571428571428),
('XPD', 'USD', 889.1721457431976),
('BSD', 'USD', 1),
('XDR', 'USD', 1.3240419194447737),
('RWF', 'USD', 0.0007577168010189062),
('AWG', 'USD', 0.5586592178770949),
('BTN', 'USD', 0.011945482024615478),
('DJF', 'USD', 0.005625544024948112),
('KMF', 'USD', 0.002197012770891467),
('ERN', 'USD', 0.06666666666666667),
('FKP', 'USD', 1.282835681986569),
('SHP', 'USD', 1.282835681986569),
('WST', 'USD', 0.35965114178318563),
('TMT', 'USD', 0.28870445113210624),
('ZMW', 'USD', 0.03820387872683574),
('CLF', 'USD', 39.35039297295132),
('MXV', 'USD', 0.4383307071373525),
('SLE', 'USD', 0.04458711945886461),
('VED', 'USD', 0.02735072771640374),
('ZWG', 'USD', 0.07257005713811952),
('ZWL', 'USD', 0.00002904284399939758);