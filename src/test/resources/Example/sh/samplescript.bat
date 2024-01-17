echo Running 'samplescript.sh' from work directory %cd%
echo System Details: %os%
echo tasklist = tasklist | findstr /i "samplescriptforwindows.sh"
mkdir testdir
ping -c 1 localhost > testdir/pingstats.txt
type "testdir/pingstats.txt"
rmdir /s /q testdir
