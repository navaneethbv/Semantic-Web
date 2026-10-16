# Semantic Web coursework

Historical Java/Jena, RDF, SPARQL, and ontology assignments.

- [Assignment 1](Assignment-1/README.md): Eclipse Java project and XML/RDF exercises.
- [Assignment 2](Assignment-2/README.md): Jena and triple-store exercises, with original worksheets.
- [Assignment 3](Assignment-3/README.md): RDF loading and SPARQL queries using the `lab3_2` Java entry point.
- [Assignment 4](Assignment-4/README.md): `Lab4.owl`, `foaf.rdf`, and ontology catalog.
- [Assignment 5](Assignment-5/README.md): `lab5.owl`, `vCard.owl`, and ontology catalog.

## Working with the projects

Import each Java project separately into Eclipse and inspect its `.classpath` and bundled libraries.
The assignments use historical Jena APIs and local file/database paths, with no repository-wide dependency lock or automated build.
Resolve input paths relative to the project's working directory before running an example.
Ontology filenames are case-sensitive on some systems; use the exact committed spelling.
