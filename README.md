### DNA-motif-affinity
Javalabra2017-4

### Mikä?

Motiiviaffiniteetti kuvaa DNA:ssa sijaitsevan lyhyen sekvenssin kykyä sitoa geeninsäätelyyn vaikuttavia proteiineja. Ohjelma määrittää frekvenssimatriisin pohjalta affiniteettiscoren, jonka avulla se analysoi mutaatioiden vaikutusta transkriptiofaktoreiden sitoutumiseen.

### Tämänhetkinen tilanne

Ohjelma ottaa syötteenä VCF-tiedoston, BED-tiedoston sekä PFM-tiedoston. Analysoi ja luo tulostiedoston.

### Dokumentaatio

[Aiheen kuvaus ja rakenne](dokumentaatio/aiheenKuvausJaRakenne.md)<br>
[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)<br>
[Checkstyle](http://htmlpreview.github.io/?https://github.com/roossh/DNA-motif-affinity/blob/master/dokumentaatio/checkstyle/checkstyle.html) (päivitetty viimeksi 2017/05/04)<br>
[Pit-raportti](http://htmlpreview.github.io/?https://github.com/roossh/DNA-motif-affinity/blob/master/dokumentaatio/pit/201705042329/index.html) (päivitetty viimeksi 2017/05/04)<br>
[JavaDoc](http://htmlpreview.github.io/?https://github.com/roossh/DNA-motif-affinity/blob/master/dokumentaatio/apidocs/index.html) (päivitetty viimeksi 2017/05/04)<br>
[Käyttöohjeet](dokumentaatio/kayttoohjeet.md)<br>
[Rakennekuvaus](dokumentaatio/rakennekuvaus.md)<br>
[Testauksesta](dokumentaatio/testidokumentaatio.md)<br>

### Jar-tiedosto

DNAMotifAffinity.jar<br>

[Pre-release](https://github.com/roossh/DNA-motif-affinity/releases/download/1.0/DNAMotifAffinity.jarhttps://github.com/roossh/DNA-motif-affinity/releases/download/1.0/DNAMotifAffinity.jarhttps://github.com/roossh/DNA-motif-affinity/releases/download/1.0/DNAMotifAffinity.jarhttps://github.com/roossh/DNA-motif-affinity/releases/download/1.0/DNAMotifAffinity.jarhttps://github.com/roossh/DNA-motif-affinity/releases/download/1.0/DNAMotifAffinity.jar)

### Käsitteistöä

#### Motiivi

Motiivilla viitataan tiettyyn pätkään DNA:ta, joka sitoo tiettyjä proteiineja. Motiivin emäsjärjestyksellä on merkitystä tämän toimintaan.

#### Affiniteetti

Affiniteetilla kuvataan motiivin taipumusta sitoa itseensä proteiineja. Affiniteetin lasku tarkoittaa, että tämän kyky sitoa proteiineja heikentyy, joka johtaa jonkinasteiseen muutokseen geeniekspressiossa.

#### Transkriptiofaktori

Kun puhutaan motiiveista, keskitytään yleensä täysin transkriptiofaktoreihin (TF). TF:t ovat proteiineja, jotka sitoutuvat tiettyyn motiiviin ja vaikuttavat geeniekspressioon joko aktivoivasti tai passivoivasti.

### Lähteet

#### Matriisit

Matriisit on ladattu [JASPAR -tietokannasta](http://jaspar.genereg.net/). JASPAR on open-source, eli heidän dataansa voi käyttää ilman rajoitteita.

#### BED-tiedostot

BED-tiedostot ladattavissa JASPARista myös.
