# FLOWViZ

## Setup

To setup FLOWViZ, the `flowviz_setup.sh` script can be used.

Run the script with the following command (inside the `scripts` directory):

```bash
./flowviz_setup.sh
```

It creates the FLOWViZ directory and clones the repository into it, and then, as the script itself tells you, it will only execute steps 1 to 3 of the manual setup that is present here: https://github.com/devandrepascoa/FLOWViZ?tab=readme-ov-file#manual-setup

**After running `flowviz_setup.sh`, you still need to execute steps 4 to 8 of the manual
setup.**

## Clean up

To clean up the setup, the `flowviz_cleanup.sh` script can be used.

Run the script with the following command (inside the `scripts` directory):

```bash
./flowviz_cleanup.sh
```

It will remove the FLOWViZ directory and all its contents, after having destroyed any running containers.

# PhyloDB

## Setup
To setup PhyloDB, the `phyloDB_setup.sh` script can be used.

Run the script with the following command (inside the `scripts` directory):

```bash
./phyloDB_setup.sh
```

It creates the PhyloDB directory and clones the repository into it, then launches the PhyloDB containers.

## Clean up

To clean up the setup, the `phyloDB_cleanup.sh` script can be used.

Run the script with the following command (inside the `scripts` directory):

```bash
./phyloDB_cleanup.sh
```

It will remove the PhyloDB directory and all its contents, after having destroyed any running containers.
