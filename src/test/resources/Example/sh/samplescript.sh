#!/bin/sh

echo Running 'samplescript.sh' from work directory $(pwd)
echo System Details: $(uname -a)
echo PPID = ${PPID}
echo PID = $$
ip addr
mkdir testdir
ping -c 1 localhost > testdir/pingstats.txt
cat testdir/pingstats.txt
rm -r testdir
