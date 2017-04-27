## Käyttöohjeet

DNA Motif Affinity Calculatoria on yksinkertaista käyttää. Avattuasi ohjelman tulee sinun valita 3 tiedostoa: VCF, BED ja PFM.

### Tiedostoista

#### VCF
VCF (Variant Call Format) on rakenteeltaan n. 9 tabulaarilla eroteltua saraketta sisältävät tiedosto. Ohjelma käsittelee näistä vain viittä ensimmäistä.

Rakenne on suunninpiirtein vastaavanlainen:

CHROM | ID | POS | REF | ALT

Jossa:

CHROM = kromosomi, jossa mutaatio on (muotoa chrN)
ID = mahdollinen rs-tunniste mutaatiolle (voi olla ".", jos tätä ei tiedetä)
POS = positio, jossa mutaatio on (indeksointi alkaa luvusta 1)
REF = referenssigenomissa oleva referenssialleeli positiolle
ALT = mutatoitunut alleeli

#### BED

BED on aluetiedosto, jossa tulee olla vähintään 3 saraketta (tabulaarilla eroteltuna!).

Rakenne:

CHROM | START | STOP

Jossa:

CHROM = kromosomi, jossa alue sijaitsee (muotoa chrN)
START = alueen alkupiste (indeksointi alkaa 1:stä)
STOP = alueen loppupiste (indeksointi alkaa 1:stä)

#### PFM

PFM (position frequency matrix), matriisi, joka sisältää N-määrän positioita (vaakataso) ja nukleotidit (sarakkeet). Jokainen solu kuvaa, kuinka monta kertaa nukleotidi B on havaittu positiossa I.

Matriisin arvojen tulee olla eroteltuja tabulaarilla.

### Ohjelman ajo

Kun ohjelmaan on valittu kaikki tarvittavat tiedostot, voi sen ajaa klikkaamalla nappia "Run". Kun ajo on valmis, ilmestyy "Done"-ikkuna.

