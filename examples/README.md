Example Simulation Scripts for Remaster
=======================================

This directory contains several example scripts which demonstrate
different kinds of phylodynamic simulations which can be performed
using ReMASTER.

To run these examples, first follow the installation instructions for Remaster
found at https://tgvaughan.github.io/remaster/#id-1-Installation. Then
simply run BEAST 2 and select one of the examples as the input file.)
Once the simulation is complete, output files will be left in the directory
containing the input file.  (If launching BEAST from the command line,
output files will be written to the current working directory.)

Note that a couple of these scripts also require the BEAST 2 package
[feast](https://tgvaughan.github.io/feast) to be installed.  These are
marked with "requires feast".

Here is a list of the example scripts contained in this directory, together
with brief summaries of the simulations they perform:

* `BDcontemp.xml`
  Simulates birth-death trajectories and trees conditioned on
  having 5 contemporaneous samples at time 5, and 5 more at time 6.

* `BDserial.xml`
  Simulates birth-death-sampling trajectories and trees.

* `Coalescent.xml`
  Simulates coalescent trees with 10 leaves under a constant
  effective population size of 1.0.

* `ComplexCoalescent.xml` (requires feast)
  Simulates coalescent trees under a more complex (piecewise constant
  and exponential) population model. Also writes .traj file
  summarizing the deterministic population dynamics.
  
* `HetCoalescent.xml` (requires feast)
  Demonstrates simulation of heterochronus coalescent trees using three
  distinct leaf sampling strategies: continuous leaf sampling over a
  time interval, even distribution of leaf times over an interval,
  and sampling of a fixed number of leaf times from a uniform distribution
  over a particular interval.

* `CoalescentInferenceTree.xml`
  Simple self-contained coalescent inference simulation study XML.
  Uses Remaster to simulate a tree under a coalescent model with a
  true constant population size of 2.0, then uses BEAST to infer the
  population size from that tree.
  
* `CoalescentInferenceAlignment.xml` (requires feast)
  A more complex self-contained coalescent inference simulation study XML.
  Uses Remaster to simulate a tree under a coalescent model with a
  true constant population size of 2.0.  Sequence evolution is then
  simulated down the tree using the sequence simulator from the
  [feast](https://tgvaugahn.github.io/feast) package under a Jukes-Cantor
  model with strict clock rate of 0.05 substitutions/site/time. Finally,
  the XML runs a BEAST MCMC analysis to jointly infer the tree, clock
  rate and population size from the simulated sequence data.
  
* `StructuredCoalescent.xml`
  Simple 2-deme structured coalescent simulation with constant
  population sizes and contemporaneously-sampled leaves.

* `StructuredCoalescentExp.xml`
  Simple 2-deme structured coalescent simulation with one constant
  and one exponentially growing population, having 1000
  contemporaneously-sampled leaves.

* `EpiBD.xml` (requires feast)
  Simple birth-death-sampling simulation parameterized in terms of
  epidemiological parameters: basic reproductive number (R0),
  becoming-uninfectious rate and sampling proportion, as defined
  in [Stadler et al., 2013](https://doi.org/10.1073/pnas.1207965110).

* `SIR.xml`
  Simulates stochastic Susceptible-Infectious-Removed (SIR) trajectories.

* `SIRdet.xml`
  Produces a trajectory file containing the numerically-integrated solution
  to the deterministic SIR ODEs. (In this case, the ODEs are treated as
  an approximation to the stochastic process.)
  This example also demonstrates the use of an end condition to terminate
  the numerical integration.

* `SEIR_tree.xml`
  Simulates a tree under the stochastic
  Susceptible-Exposed-Infectious-Removed (SEIR) model, under a continuous
  sampling process which only activates after 5 time units.


* `SEIRdet_tree.xml`
  Simulates a tree using a coalescent approximation to the stochastic
  Susceptible-Exposed-Infectious-Removed (SEIR) model with an explicit
  termination condition and punctual sampling reactions.

* `SimulatedBDAlignment.xml` (requires feast)
  Simulates a tree under a birth-death-sampling model, then simulates
  sequence evolution under a basic Jukes-Cantor substitution model to
  produce a corresponding simulated alignment.

* `YuleInferenceInitialisation.xml`
  Demonstrates the use of ReMASTER to initilise the state of
  an MCMC analysis using a simulated birth-death tree.
