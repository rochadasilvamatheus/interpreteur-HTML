# Interpreteur-HTML
### Un interpréteur pour l’édition de texte en HTML

Le but du projet est de permettre la traduction d’un texte écrit dans le langage **SimpleText**
(inspiré du LATEX) en HTML.

## La grammaire

```
document -> Corps
corps ->\begindoc suite_elements \enddoc
suite_elements-> element suite_elements | £
element-> mot
| \linebreak
| \bf{ suite_elements }
| \it{ suite_elements }
| enumeration
enumeration-> \beginenum suite_items \endenum
suite_items-> item suite_items |£
item-> \item suite_elements
```

## Sémantique du langage
Le corps du document correspond au texte proprement dit :

- les mots sont les mots du texte.
- **\linebreak** correspond à un passage à la ligne.
- **\bf{** *suite_elements* **}** veut dire que le texte entre accolades sera en gras (bold face).
- **\it{** *suite_elements* **}** veut dire que le texte entre accolades sera en italique.

Les ITEM d’une énumération sont écrits en retrait et numéroté.
