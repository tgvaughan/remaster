ReMASTER
========

This directory contains the source code for ReMASTER, a complete rewrite of the
[MASTER](https://tgvaughan.github.io/MASTER) phylogenetic tree simulation package
for [BEAST 2](https://beast2.org).

It aims to address the following problems with MASTER:
1. slow and memory-intensive tree simulation,
2. awkward coalescent simulation,
3. difficult-to-configure sampling in BD models,
4. poor integration with BEAST,
5. inflexible design.

This repository is primarily of interest to people keen on building
ReMASTER from the source or adapating it for use in other software. If
this doesn't include you, please instead visit the project web page
at:

https://tgvaughan.github.io/remaster

There you'll find all relevant usage information including
installation, step-by-step instructions and examples.  ReMASTER is
also described in the following publication:

> T. G. Vaughan, "ReMASTER: Improved phylodynamic simulation for BEAST 2.7",
> Bioinformatics, btae015, 2024. (https://doi.org/10.1093/bioinformatics/btae015)

Happy simulating!

[![Build Status](https://github.com/tgvaughan/remaster/workflows/Unit%2Fintegration%20tests/badge.svg)](https://github.com/tgvaughan/remaster/actions?query=workflow%3A%22Unit%2Fintegration+tests%22)

Building from Source
--------------------

To build ReMASTER from source you'll need the following to be installed:
- OpenJDK version 17 or greater
- the Apache Ant build system

Once these are installed and in your execution path, running `ant` with
no arguments from the root directory of the ReMASTER repository should
build the package and create the corresponding ZIP file, which will be
left in the `dist/` subdirectory.

Note that unless you already have a local copy of the latest [beast 2
source](https://github.com/CompEvol/beast2) in the directory
`../beast2` relative to the ReMASTER root, the build script will
attempt to download it automatically.  Thus, most builds will require
a network connection.

License
-------

ReMASTER is free (as in freedom) software, and is released under the
terms of version 3 or later of the GNU General Public License. A copy
of this license can be found in this directory in the file named COPYING.
