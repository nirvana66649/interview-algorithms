"""Check catalog consistency, local Markdown links, and prohibited file types."""
import json
import re
from pathlib import Path
from urllib.parse import unquote, urlsplit

root = Path(__file__).resolve().parents[1]
catalog = json.loads((root / 'catalog.json').read_text(encoding='utf-8'))
entries = catalog['entries']
ids = [entry['id'] for entry in entries]
assert len(ids) == len(set(ids)), 'Duplicate entry IDs'
assert len(catalog['topics']) == 19, 'Unexpected initial topic count'
assert len(entries) >= 513, 'Initial migration entries missing'
for entry in entries:
    assert entry['topic'] in catalog['topics'], entry
    assert entry['status'] in ('index', 'documented'), entry
    for field in ('solution', 'java'):
        if entry.get(field):
            assert (root / entry[field]).is_file(), entry
    if entry['status'] == 'documented':
        assert entry.get('solution') and entry.get('java') and entry.get('source'), entry

links = 0
for path in root.rglob('*.md'):
    if '.git' in path.parts:
        continue
    text = path.read_text(encoding='utf-8')
    for target in re.findall(r'\]\(([^\s)]+)\)', text):
        if urlsplit(target).scheme or target.startswith('#'):
            continue
        relative = unquote(target.split('#', 1)[0])
        assert (path.parent / relative).resolve().is_relative_to(root), target
        assert (path.parent / relative).exists(), (str(path), target)
        links += 1

for path in root.rglob('*'):
    if '.git' in path.parts or not path.is_file():
        continue
    assert path.suffix.lower() not in {'.docx', '.pdf', '.png', '.jpeg', '.zip'}, path
    if path.suffix in {'.md', '.json', '.java', '.yml'}:
        text = path.read_text(encoding='utf-8')
        assert not re.search(r'[\w.+-]+@[\w.-]+\.[A-Za-z]{2,}', text), f'Email in {path}'
        assert 'libfile_' not in text, f'Private file identifier in {path}'
        assert 'docs.google.com/document/d/' not in text, f'Personal document link in {path}'

print(f'PASS: {len(catalog["topics"])} topics, {len(entries)} indexed entries, {links} local links.')
