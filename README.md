### DNA-motif-affinity
Javalabra2017-4

### Mikä?

Motiiviaffiniteetti kuvaa DNA:ssa sijaitsevan lyhyen sekvenssin kykyä sitoa geeninsäätelyyn vaikuttavia proteiineja. Ohjelma määrittää frekvenssimatriisin pohjalta affiniteettiscoren, jonka avulla se analysoi mutaatioiden vaikutusta transkriptiofaktoreiden sitoutumiseen.

### Tämänhetkinen tilanne

Juuri nyt ohjelma lukee matriiseja (ehdolla, että nämä ovat tabillä eroteltuja, muuten ohjelma ei toimi) ja analysoi näistä scoren mutaatioille tietyissä positioissa. Vielä ohjelma ei lue VCF-tiedostosta suoraan mutaatioita analyysiä varten, joten mutaatiot toimivat pitkälti vain merkkijonojen perusteella (kuitenkin tiedostoja tullaan lukemaan merkkijonoina, joten logiikka on jo olemassa).

### Dokumentaatio

[Aiheen kuvaus ja rakenne](dokumentaatio/aiheenKuvausJaRakenne.md)
[Checkstyle](dokumentaatio/checkstyle.html) (päivitetty viimeksi 2017/03/30)
[Pit-raportti](dokumentaatio/pit/201703302307/index.html) (päivitetty viimeksi 2017/03/30)

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

TBA
