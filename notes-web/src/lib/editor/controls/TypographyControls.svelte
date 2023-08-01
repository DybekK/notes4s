<script lang="ts">
    import {Button, Dropdown, DropdownDivider, DropdownItem} from 'flowbite-svelte'
    import {editorStore} from "$lib/editor.store";

    const {store: editor} = editorStore
</script>

<Button color="light" size="xs" class="styles-button">Aa</Button>
<Dropdown classContainer="backdrop-filter backdrop-blur-md bg-white/70 dark:bg-gray-800/50" triggeredBy=".styles-button">
    <DropdownItem class="font-extrabold" on:click={() => $editor.chain().focus().toggleHeading({level: 1}).run()}>
        Title
    </DropdownItem>
    <DropdownItem class="font-bold" on:click={() => $editor.chain().focus().toggleHeading({level: 2}).run()}>Header
    </DropdownItem>
    <DropdownItem class="font-semibold" on:click={() => $editor.chain().focus().toggleHeading({level: 3}).run()}>
        Subheading
    </DropdownItem>
    <DropdownItem on:click={() => $editor.chain().focus().setParagraph().run()}>Paragraph</DropdownItem>
    <DropdownDivider/>
    <DropdownItem class="text-xs" on:click={() => $editor.chain().focus().toggleBulletList().run()}>- Bullet list
    </DropdownItem>
    <DropdownItem class="text-xs" on:click={() => $editor.chain().focus().toggleOrderedList().run()}>1. Ordered list
    </DropdownItem>
    <DropdownDivider/>
    <DropdownItem class="text-xs" on:click={() => $editor.chain().focus().toggleCodeBlock().run()}>Code block
    </DropdownItem>
</Dropdown>