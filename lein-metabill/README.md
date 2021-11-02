# lein-metabill

A Leiningen plugin to put meta info into the manifest file.

## Usage

Put `[lein-metabill "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your project.clj.
You can use a profile: `plugin.lein-metabill/default`

For example:

```
$ lein with-profile base,dev,plugin.lein-metabill/default repl
```
